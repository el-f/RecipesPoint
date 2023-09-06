package com.recipespoint.backend.dal.repository;

import com.recipespoint.backend.dal.model.RecipeQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRecipeQueryRepository extends JpaRepository<RecipeQueryEntity, Long> {

    @Query("SELECT r FROM RecipeQueryEntity r WHERE " +
            "(r.offset <= :offsetUpperBound AND (r.offset + r.number) >= :offsetLowerBound) AND " +
            "(:category = r.category) AND " +
            "(:cuisine = r.cuisine) AND " +
            "(:diet = r.diet) AND " +
            "(:freeText = r.freeText) " +
            "ORDER BY r.offset ASC")
    List<RecipeQueryEntity> getAllByFilters(
            Integer offsetLowerBound, Integer offsetUpperBound,
            String category, String cuisine, String diet, String freeText);

}
