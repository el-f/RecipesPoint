package com.recipespoint.backend.dal.model.enums;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public enum Diet {
    GLUTEN_FREE("gluten free"),
    DAIRY_FREE("dairy free"),
    KETOGENIC("ketogenic"),
    VEGETARIAN("vegetarian"),
    LACTO_VEGETARIAN("lacto vegetarian"),
    OVO_VEGETARIAN("ovo vegetarian"),
    LACTO_OVO_VEGETARIAN("lacto ovo vegetarian"),
    VEGAN("vegan"),
    PROLETARIAN("pescatarian"),
    PALEOLITHIC("paleolithic"),
    PRIMAL("primal"),
    FODMAP("fodmap friendly"),
    WHOLE30("whole30");


    private final String label;

    public final String toString() {
        return label;
    }

    private static final Map<String, Diet> LABEL_TO_ENUM = new HashMap<>();

    static {
        for (Diet diet : values()) {
            LABEL_TO_ENUM.put(diet.toString().toLowerCase(), diet);
        }
    }

    public static Diet fromLabel(String label) {
        Diet diet = LABEL_TO_ENUM.get(label.toLowerCase());
        if (diet == null) {
            log.error("No Diet enum constant found for label: '" + label + "'");
        }
        return diet;
    }

}
