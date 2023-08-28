package com.recipespoint.backend.controller;


import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.dto.RecipeQuery;
import com.recipespoint.backend.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public RecipeDto addRecipe(@RequestBody RecipeDto recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PostMapping("/get")
    public Collection<RecipeDto> getRecipes(@Valid @RequestBody RecipeQuery query) {
        return recipeService.getRecipes(query);
    }

}
