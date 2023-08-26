package com.recipespoint.backend.dto;

import java.util.Collection;

public record SpoonacularResponse(
        Long offset,
        Long number,
        Long totalResults,
        Collection<RecipeDto> results
) {}
