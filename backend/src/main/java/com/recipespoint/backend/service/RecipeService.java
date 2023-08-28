package com.recipespoint.backend.service;

import com.recipespoint.backend.dal.model.RecipeEntity;
import com.recipespoint.backend.dal.model.RecipeQueryEntity;
import com.recipespoint.backend.dal.repository.IRecipeQueryRepository;
import com.recipespoint.backend.dal.repository.IRecipeRepository;
import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.dto.RecipeQuery;
import com.recipespoint.backend.util.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RecipeService {

    private final IRecipeRepository recipeRepo;

    private final IRecipeQueryRepository recipeQueryRepo;

    private final RecipeMapper recipeMapper;

    private final SpoonacularService spoonacularService;

    @Autowired
    public RecipeService(
            IRecipeRepository recipeRepo,
            IRecipeQueryRepository recipeQueryRepo,
            RecipeMapper recipeMapper,
            SpoonacularService spoonacularService
    ) {
        this.recipeRepo = recipeRepo;
        this.recipeMapper = recipeMapper;
        this.recipeQueryRepo = recipeQueryRepo;
        this.spoonacularService = spoonacularService;
    }

    public RecipeDto addRecipe(RecipeDto recipe) {
        log.info("Adding recipe with id: {}", recipe.id());
        RecipeEntity entity = recipeMapper.recipeDtoToRecipeEntity(recipe);

        RecipeEntity savedEntity = recipeRepo.save(entity);

        return recipeMapper.recipeEntityToRecipeDto(savedEntity);
    }

    public List<RecipeDto> getRecipes(RecipeQuery query) {
        try {
            log.info("Getting recipes for query {}", query);
            return getRecipesUsingCache(query);
        } catch (Exception e) {
            log.error("Failed to get recipes from cache, querying API", e);
            return spoonacularService.getRecipes(query);
        }
    }

    // *****************************************************************************************************************
    // ************************************* QUERIES CACHE MANAGEMENT **************************************************
    // *****************************************************************************************************************

    /**
     * Get recipes from the DB or from the API if not cached.
     * As explained in {@link RecipeQuery}, to minimize the API requests due to Spoonacular's daily limit,
     * we cache query results, either wholly or partially, for efficient reuse.
     *
     * @param query the query to get the recipes by.
     * @return the recipes.
     */
    public List<RecipeDto> getRecipesUsingCache(RecipeQuery query) {
        int offset = query.offset();
        int number = query.number();
        String category = query.category();
        String cuisine = query.cuisine();
        String diet = query.diet();
        String freeText = query.freeText();

        List<RecipeQueryEntity> caches = recipeQueryRepo.getAllByFilters(
                offset,
                offset + number,
                category,
                cuisine,
                diet,
                freeText
        );
        List<RecipeQueryEntity> newCaches = new ArrayList<>();

        List<RecipeEntity> recipes = new ArrayList<>();
        int neededEndExclusive = offset + number;
        int currentOffset = offset;

        for (final RecipeQueryEntity cache : caches) {
            int cacheStart = cache.getOffset();
            int cacheEnd = cache.getOffset() + cache.getNumber();

            if (cacheEnd < offset) continue;                // cache is before the needed range
            if (cacheStart >= neededEndExclusive) break;    // cache is after the needed range

            // we did not get to the cache yet, so we need to query the API
            if (currentOffset < cacheStart) {
                collectRecipes(
                        new RecipeQuery(offset, cacheStart - currentOffset, diet, cuisine, category, freeText),
                        newCaches,
                        recipes
                );

                currentOffset = cacheStart; // now we are at the cache
            }

            // limit the start to the cache size
            int start = Math.max(currentOffset - cache.getOffset(), 0);
            // limit the end to the cache size
            int end = Math.min(neededEndExclusive - cache.getOffset(), cache.getNumber() - 1);

            if (start > end) {
                log.error("got invalid range {}-{} in query {}, stopping cache check", start, end, query);
                break;
            }

            log.info(
                    "Get from DB for range {}-{} in query {}",
                    start,
                    end,
                    query
            );

            List<RecipeEntity> cachedRecipes = cache.getRecipes();

            if (cachedRecipes.size() < end || cachedRecipes.size() < start) {
                log.error("got invalid range {}-{} in query {}, stopping cache check", start, end, query);
                break;
            }

            recipes.addAll(cachedRecipes.subList(start, end));
            currentOffset = cacheEnd;
        }

        // we ran out of caches, but we still need to query the API (offset is still less than neededEndExclusive)
        if (currentOffset < neededEndExclusive) {
            collectRecipes(
                    new RecipeQuery(currentOffset,
                                    neededEndExclusive - currentOffset,
                                    diet,
                                    cuisine,
                                    category,
                                    freeText),
                    newCaches,
                    recipes
            );
        }

        if (!newCaches.isEmpty()) {
            attachExistingRecipes(newCaches);

            // by saving all the caches, we also save the recipes in them (cascade);
            List<RecipeQueryEntity> mergedCaches = mergeCaches(caches, newCaches);

            log.info("Merged caches: {}", mergedCaches);
            recipeQueryRepo.saveAllAndFlush(mergedCaches);

            log.info("Successfully merged {} existing caches with {} new caches", caches.size(), newCaches.size());
        }

        return recipes.stream()
                .map(recipeMapper::recipeEntityToRecipeDto)
                .toList();
    }

    /**
     * some queries will return recipes that are already in the DB, so we need them to be attached to the DB before we any further processing.
     *
     * @param caches the caches to merge with the DB.
     */
    private void attachExistingRecipes(List<RecipeQueryEntity> caches) {
        caches.forEach(cache -> {
            Map<Long, RecipeEntity> alreadyAttached = new HashMap<>();
            cache.getRecipes().forEach(recipe -> {
                recipeRepo.findById(recipe.getId()).ifPresent(rcp -> alreadyAttached.put(rcp.getId(), rcp));
            });
            cache.setRecipes(cache.getRecipes().stream()
                                     .map(recipe -> alreadyAttached.getOrDefault(recipe.getId(), recipe))
                                     .toList());
        });
    }

    private void collectRecipes(
            RecipeQuery query,
            List<RecipeQueryEntity> newCaches,
            List<RecipeEntity> recipes
    ) {
        List<RecipeDto> newRecipesDtos = spoonacularService.getRecipes(query);
        log.info("Got {} recipes from API for query {}", newRecipesDtos.size(), query);

        List<RecipeEntity> newRecipes = newRecipesDtos.stream()
                .map(recipeMapper::recipeDtoToRecipeEntity)
                .toList();

        log.info("Mapped {} recipes from API for query {}", newRecipes.size(), query);

        recipes.addAll(newRecipes);

        newCaches.add(new RecipeQueryEntity(query, newRecipes));
    }

    public List<RecipeQueryEntity> mergeCaches(List<RecipeQueryEntity> existingCaches, List<RecipeQueryEntity> newCaches) {
        log.info("Merging {} existing caches with {} new caches", existingCaches.size(), newCaches.size());
        log.info("Existing caches: {}", existingCaches);
        log.info("New caches: {}", newCaches);
        if (existingCaches.isEmpty()) return newCaches;
        if (newCaches.isEmpty()) return existingCaches;

        List<RecipeQueryEntity> allCaches = new ArrayList<>(existingCaches);
        allCaches.addAll(newCaches);

        allCaches.sort(Comparator.comparingInt(RecipeQueryEntity::getOffset));

        List<RecipeQueryEntity> mergedCaches = new ArrayList<>();
        List<RecipeQueryEntity> markedForDelete = new ArrayList<>();
        RecipeQueryEntity currentCache = allCaches.get(0);

        for (int i = 1; i < allCaches.size(); i++) {
            RecipeQueryEntity nextCache = allCaches.get(i);

            int currentCacheEndExclusive = currentCache.getOffset() + currentCache.getNumber();
            int nextCacheStart = nextCache.getOffset();

            if (currentCacheEndExclusive >= nextCacheStart) {
                List<RecipeEntity> mergedRecipes = new ArrayList<>(currentCache.getRecipes());
                mergedRecipes.addAll(nextCache.getRecipes());
                markedForDelete.add(nextCache); // mark the cache for deletion

                currentCache.setRecipes(mergedRecipes);
                currentCache.setNumber(mergedRecipes.size());
            } else {
                mergedCaches.add(currentCache);
                currentCache = nextCache;
            }
        }

        recipeQueryRepo.deleteAll(markedForDelete);

        mergedCaches.add(currentCache);

        return mergedCaches;
    }

}
