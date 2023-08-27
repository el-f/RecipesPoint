package com.recipespoint.backend.dal.model;

import com.recipespoint.backend.dto.RecipeQuery;
import com.recipespoint.backend.util.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/**
 * Since Spoonacular's API is limiting the number of requests per day so heavily (150 per day),
 * any simple usage of our app would quickly exceed the limit. To avoid this, we need to cache
 * which queries have already been covered or even partially covered.
 * This class is used to store the results of the queries in the database.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class RecipeQueryEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String diet;
    private String cuisine;
    private String category;
    private String freeText;
    private Integer offset;
    private Integer number;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @ToString.Exclude
    private List<RecipeEntity> recipes;


    public RecipeQueryEntity(RecipeQuery query, List<RecipeEntity> recipes) {
        this.offset = query.offset();
        this.number = query.number();
        this.diet = query.diet();
        this.cuisine = query.cuisine();
        this.category = query.category();
        this.freeText = query.freeText();
        this.recipes = recipes;
    }

}
