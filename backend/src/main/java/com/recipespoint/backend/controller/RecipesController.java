package com.recipespoint.backend.controller;


import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.dto.RecipeQuery;
import com.recipespoint.backend.service.RecipesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/hello/{name}")
    public String getHello(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/add")
    public RecipeDto addRecipe(@RequestBody RecipeDto recipe) {
        return recipesService.addRecipe(recipe);
    }

    @PostMapping("/search")
    public Collection<RecipeDto> searchRecipes(@Valid @RequestBody RecipeQuery query) {
        return recipesService.getRecipes(query);
    }

    @GetMapping("/favorites/{userId}")
    public Collection<RecipeDto> getFavorites(@PathVariable String userId) {
        return recipesService.getFavorites(userId);
    }

}
