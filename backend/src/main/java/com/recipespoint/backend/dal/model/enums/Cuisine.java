package com.recipespoint.backend.dal.model.enums;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Slf4j
public enum Cuisine {
    AFRICAN("African"),
    ASIAN("Asian"),
    AMERICAN("American"),
    BRITISH("British"),
    CAJUN("Cajun"),
    CARIBBEAN("Caribbean"),
    CHINESE("Chinese"),
    EASTERN_EUROPEAN("Eastern European"),
    EUROPEAN("European"),
    FRENCH("French"),
    GERMAN("German"),
    GREEK("Greek"),
    INDIAN("Indian"),
    IRISH("Irish"),
    ITALIAN("Italian"),
    JAPANESE("Japanese"),
    JEWISH("Jewish"),
    KOREAN("Korean"),
    LATIN_AMERICAN("Latin American"),
    MEDITERRANEAN("Mediterranean"),
    MEXICAN("Mexican"),
    MIDDLE_EASTERN("Middle Eastern"),
    NORDIC("Nordic"),
    SOUTHERN("Southern"),
    SPANISH("Spanish"),
    THAI("Thai"),
    VIETNAMESE("Vietnamese");

    private final String label;

    public final String toString() {
        return label;
    }

    private static final Map<String, Cuisine> LABEL_TO_ENUM = new HashMap<>();

    static {
        for (Cuisine cuisine : values()) {
            LABEL_TO_ENUM.put(cuisine.toString().toLowerCase(), cuisine);
        }
    }

    public static Cuisine fromLabel(String label) {
        Cuisine cuisine = LABEL_TO_ENUM.get(label.toLowerCase());
        if (cuisine == null) {
            log.error("No Cuisine enum constant found for label: '" + label + "'");
        }
        return cuisine;
    }

}
