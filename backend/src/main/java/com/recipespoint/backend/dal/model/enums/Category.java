package com.recipespoint.backend.dal.model.enums;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Slf4j
public enum Category {
    FINGERFOOD("fingerfood"),
    ANTIPASTI("antipasti"),
    STARTER("starter"),
    SNACK("snack"),
    ANTIPASTO("antipasto"),
    HORDOEUVRE("hor d'oeuvre"),
    MAIN_COURSE("main course"),
    SIDE_DISH("side dish"),
    DESSERT("dessert"),
    APPETIZER("appetizer"),
    SALAD("salad"),
    BREAD("bread"),
    BREAKFAST("breakfast"),
    SOUP("soup"),
    BEVERAGE("beverage"),
    SAUCE("sauce");

    private final String label;

    public final String toString() {
        return label;
    }

    private static final Map<String, Category> LABEL_TO_ENUM = new HashMap<>();

    static {
        for (Category category : values()) {
            LABEL_TO_ENUM.put(category.toString().toLowerCase(), category);
        }
    }

    public static Category fromLabel(String label) {
        Category category = LABEL_TO_ENUM.get(label.toLowerCase());
        if (category == null) {
            log.error("No Category enum constant found for label: '" + label + "'");
        }
        return category;
    }

}
