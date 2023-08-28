package com.recipespoint.backend.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record RecipeQuery(
        @Range(min = 0, max = 900)
        @NotNull
        Integer offset,

        @Range(min = 1, max = 100)
        @NotNull
        Integer number,

        String diet,
        String cuisine,
        String category,
        String freeText
) {

    public String getAsParams() {
        String params = "offset=" + offset + "&number=" + number;
        if (diet != null) params += "&diet=" + diet;
        if (cuisine != null) params += "&cuisine=" + cuisine;
        if (category != null) params += "&type=" + category;
        if (freeText != null) params += "&query=" + freeText;
        return params;
    }

}
