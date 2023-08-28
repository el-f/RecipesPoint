package com.recipespoint.backend.controller;

import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/favorite-recipes/{userId}")
    public Collection<RecipeDto> getFavoriteRecipes(@PathVariable("userId") String userId) {
        return userService.getFavoriteRecipes(userId);
    }

    @PostMapping("/favorite-recipes/{userId}/{recipeId}")
    public void addFavoriteRecipe(@PathVariable("userId") String userId, @PathVariable("recipeId") Long recipeId) {
        userService.addFavoriteRecipe(userId, recipeId);
    }

    @DeleteMapping("/favorite-recipes/{userId}/{recipeId}")
    public void removeFavoriteRecipe(@PathVariable("userId") String userId, @PathVariable("recipeId") Long recipeId) {
        userService.removeFavoriteRecipe(userId, recipeId);
    }

}
