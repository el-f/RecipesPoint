package com.recipespoint.backend.service;

import com.recipespoint.backend.dal.model.RecipeEntity;
import com.recipespoint.backend.dal.model.UserEntity;
import com.recipespoint.backend.dal.repository.IRecipeRepository;
import com.recipespoint.backend.dal.repository.IUserRepository;
import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.util.RecipeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class UserService {
    private final IUserRepository userRepository;

    private final RecipeMapper recipeMapper;

    private final IRecipeRepository recipeRepository;

    @Autowired
    public UserService(IUserRepository userRepository, RecipeMapper recipeMapper, IRecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;

    }

    public Collection<RecipeDto> getFavoriteRecipes(String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));

        log.info("Getting favorite recipes for user: {}", user);
        return user.getFavoriteRecipes().stream().map(recipeMapper::recipeEntityToRecipeDto).toList();
    }

    public void addFavoriteRecipe(String userId, Long recipeId) {
        RecipeEntity recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found for id: " + recipeId));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));

        user.getFavoriteRecipes().add(recipe);

        userRepository.save(user);

        log.info("Added recipe with id: {} to favorites for user: {}", recipeId, user.getId());
    }

    public void removeFavoriteRecipe(String userId, Long recipeId) {
        RecipeEntity recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found for id: " + recipeId));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));

        user.getFavoriteRecipes().remove(recipe);

        userRepository.save(user);

        log.info("Removed recipe with id: {} from favorites for user: {}", recipeId, user.getId());
    }

}
