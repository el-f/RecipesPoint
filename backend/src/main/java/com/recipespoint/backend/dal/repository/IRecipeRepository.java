package com.recipespoint.backend.dal.repository;

import com.recipespoint.backend.dal.model.RecipeEntity;
import com.recipespoint.backend.dal.model.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecipeRepository extends JpaRepository<RecipeEntity, Long> {

    /*
     * Since specifically there is no business logic that needs these methods, and even our search just uses cached queries,
     * these methods remain unused, but I left them here for completeness’s sake.
     */

    List<RecipeEntity> findAllByCuisinesContaining(Cuisine cuisine);

    Page<RecipeEntity> findByCuisinesContaining(Cuisine cuisine, Pageable pageable);

    List<RecipeEntity> findAllByDietsContaining(Diet diet);

    Page<RecipeEntity> findByDietsContaining(Diet diet, Pageable pageable);

    List<RecipeEntity> findAllByCategoriesContaining(Category category);

    Page<RecipeEntity> findByCategoriesContaining(Category category, Pageable pageable);

    List<RecipeEntity> findAllByTitleContaining(String title);

    Page<RecipeEntity> findByTitleContaining(String title, Pageable pageable);

}
