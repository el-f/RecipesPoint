package com.recipespoint.backend.util;


import com.recipespoint.backend.dal.model.RecipeEntity;
import com.recipespoint.backend.dal.model.enums.*;
import com.recipespoint.backend.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {

    @Autowired
    ObjectMapperProvider objectMapperProvider;

    @Mapping(target = "cuisines", expression = "java(mapCuisinesFromLabels(recipe.cuisines()))")
    @Mapping(target = "diets", expression = "java(mapDietsFromLabels(recipe.diets()))")
    @Mapping(target = "categories", expression = "java(mapCategoriesFromLabels(recipe.dishTypes()))")
    @Mapping(target = "analyzedInstructionsData", expression = "java(objectMapperProvider.mapAnalyzedInstructionsToString(recipe.analyzedInstructions()))")
    public abstract RecipeEntity recipeDtoToRecipeEntity(RecipeDto recipe);

    @Mapping(target = "cuisines", expression = "java(mapCuisinesToLabels(entity.getCuisines()))")
    @Mapping(target = "diets", expression = "java(mapDietsToLabels(entity.getDiets()))")
    @Mapping(target = "dishTypes", expression = "java(mapCategoriesToLabels(entity.getCategories()))")
    @Mapping(target = "analyzedInstructions", expression = "java(objectMapperProvider.mapStringToAnalyzedInstructions(entity.getAnalyzedInstructionsData()))")
    public abstract RecipeDto recipeEntityToRecipeDto(RecipeEntity entity);

    public Set<Cuisine> mapCuisinesFromLabels(Collection<String> cuisines) {
        return cuisines.stream()
                .map(Cuisine::fromLabel)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Collection<String> mapCuisinesToLabels(Collection<Cuisine> cuisines) {
        return cuisines.stream()
                .map(Cuisine::toString)
                .toList();
    }

    public Set<Diet> mapDietsFromLabels(Collection<String> diets) {
        return diets.stream()
                .map(Diet::fromLabel)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Collection<String> mapDietsToLabels(Collection<Diet> diets) {
        return diets.stream()
                .map(Diet::toString)
                .toList();
    }

    public Set<Category> mapCategoriesFromLabels(Collection<String> categories) {
        return categories.stream()
                .map(Category::fromLabel)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Collection<String> mapCategoriesToLabels(Collection<Category> categories) {
        return categories.stream()
                .map(Category::toString)
                .toList();
    }

}
