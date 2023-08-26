package com.recipespoint.backend.dto;

import java.util.Collection;

public record RecipeDto(
        Long id,
        Boolean vegetarian,
        Boolean vegan,
        Boolean glutenFree,
        Boolean dairyFree,
        Boolean veryHealthy,
        Boolean cheap,
        Boolean veryPopular,
        Boolean sustainable,
        Boolean lowFodmap,
        Integer weightWatcherSmartPoIntegers,
        String gaps,
        Integer preparationMinutes,
        Integer cookingMinutes,
        Integer aggregateLikes,
        Integer healthScore,
        String creditsText,
        String license,
        String sourceName,
        Double pricePerServing,
        String title,
        Integer readyInMinutes,
        Integer servings,
        String sourceUrl,
        String spoonacularSourceUrl,
        String image,
        String imageType,
        String summary,

        Collection<String> cuisines,

        Collection<String> dishTypes,

        Collection<String> diets,

        Collection<String> occasions,

        Collection<AnalyzedInstruction> analyzedInstructions
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
