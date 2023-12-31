package com.recipespoint.backend.dal.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserEntity {
    @Id
//    @UuidGenerator // since we use a dummy to demonstrate the project and don't have real users
    private String id;

    private String username;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @ToString.Exclude
    private Set<RecipeEntity> favoriteRecipes;

}


