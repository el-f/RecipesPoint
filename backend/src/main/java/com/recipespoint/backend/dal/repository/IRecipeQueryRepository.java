package com.recipespoint.backend.dal.repository;

import com.recipespoint.backend.dal.model.RecipeQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRecipeQueryRepository extends JpaRepository<RecipeQueryEntity, Long> {

    @Query("SELECT r FROM RecipeQueryEntity r WHERE " +
            "(r.offset <= :offsetUpperBound AND (r.offset + r.number) >= :offsetLowerBound) AND " +
            "(:category IS NULL OR r.category LIKE %:category%) AND " +
            "(:cuisine IS NULL OR r.cuisine LIKE %:cuisine%) AND " +
            "(:diet IS NULL OR r.diet LIKE %:diet%) AND " +
            "(:freeText IS NULL OR r.freeText LIKE %:freeText%)" +
            "ORDER BY r.offset ASC")
    List<RecipeQueryEntity> getAllByFilters(
            Integer offsetLowerBound, Integer offsetUpperBound,
            String category, String cuisine, String diet, String freeText);

}
