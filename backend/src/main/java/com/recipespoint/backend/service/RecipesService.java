package com.recipespoint.backend.service;

import com.recipespoint.backend.dal.model.RecipeEntity;
import com.recipespoint.backend.dal.repository.IRecipeRepository;
import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.util.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RecipesService {

    private final IRecipeRepository recipeRepo;

    private final RecipeMapper recipeMapper;

    @Autowired
    public RecipesService(
            IRecipeRepository recipeRepo,
            RecipeMapper recipeMapper
    ) {
        this.recipeRepo = recipeRepo;
        this.recipeMapper = recipeMapper;
    }

    public RecipeDto addRecipe(RecipeDto recipe) {
        log.info("Adding recipe with id: {}", recipe.id());
        RecipeEntity entity = recipeMapper.recipeDtoToRecipeEntity(recipe);

        RecipeEntity savedEntity = recipeRepo.save(entity);

        return recipeMapper.recipeEntityToRecipeDto(savedEntity);
    }

}
