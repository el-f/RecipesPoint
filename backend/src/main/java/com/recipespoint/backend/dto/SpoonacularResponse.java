package com.recipespoint.backend.dto;

import java.util.List;

public record SpoonacularResponse(
        Integer offset,
        Integer number,
        Integer totalResults,
        List<RecipeDto> results
) {}
