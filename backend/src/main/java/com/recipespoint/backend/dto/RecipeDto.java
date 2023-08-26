package com.recipespoint.backend.dto;

import java.util.Collection;

public record RecipeDto(
        Boolean cheap,
        Boolean dairyFree,
        Boolean glutenFree,
        Boolean lowFodmap,
        Boolean sustainable,
        Boolean vegan,
        Boolean vegetarian,
        Boolean veryHealthy,
        Boolean veryPopular,
        Double pricePerServing,
        Integer aggregateLikes,
        Integer cookingMinutes,
        Integer healthScore,
        Integer preparationMinutes,
        Integer readyInMinutes,
        Integer servings,
        Integer weightWatcherSmartPoIntegers,
        Long id,
        String creditsText,
        String gaps,
        String image,
        String imageType,
        String license,
        String sourceName,
        String sourceUrl,
        String spoonacularSourceUrl,
        String summary,
        String title,
        Collection<AnalyzedInstruction> analyzedInstructions,
        Collection<String> cuisines,
        Collection<String> diets,
        Collection<String> dishTypes,
        Collection<String> occasions
) {
    public record AnalyzedInstruction(
            String name,
            Collection<Step> steps
    ) {
        public record Step(
                Integer number,
                String step,
                Collection<Item> ingredients,
                Collection<Item> equipment
        ) {
            public record Item(
                    Long id,
                    String name,
                    String localizedName,
                    String image
            ) {}
        }
    }
}
