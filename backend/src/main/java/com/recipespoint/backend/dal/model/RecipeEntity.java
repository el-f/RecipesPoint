package com.recipespoint.backend.dal.model;

import com.recipespoint.backend.dal.model.enums.*;
import com.recipespoint.backend.util.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class RecipeEntity extends Auditable {

    @Id
    private Long id;

    private Boolean cheap;
    private Boolean dairyFree;
    private Boolean glutenFree;
    private Boolean lowFodmap;
    private Boolean sustainable;
    private Boolean vegan;
    private Boolean vegetarian;
    private Boolean veryHealthy;
    private Boolean veryPopular;
    private Double pricePerServing;
    private Integer aggregateLikes;
    private Integer cookingMinutes;
    private Integer healthScore;
    private Integer preparationMinutes;
    private Integer readyInMinutes;
    private Integer servings;
    private Integer weightWatcherSmartPoIntegers;
    private String creditsText;
    private String gaps;
    private String image;
    private String imageType;
    private String license;
    private String sourceName;
    private String sourceUrl;
    private String spoonacularSourceUrl;
    private String title;

    @Lob
    @Column(name = "summary", columnDefinition = "LONGTEXT")
    private String summary;

    @Lob
    @Column(name = "analyzed_instructions", columnDefinition = "LONGTEXT")
    private String analyzedInstructionsData;

    @ElementCollection
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    @ElementCollection
    @JoinTable(name = "recipe_cuisine", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "cuisine")
    @Enumerated(EnumType.STRING)
    private Set<Cuisine> cuisines;

    @ElementCollection
    @JoinTable(name = "recipe_diet", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "diet")
    @Enumerated(EnumType.STRING)
    private Set<Diet> diets;

    @ElementCollection
    private Collection<String> occasions;

}
