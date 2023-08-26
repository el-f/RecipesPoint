package com.recipespoint.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipespoint.backend.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ObjectMapperProvider {

    private final ObjectMapper objectMapper;

    @Autowired
    public ObjectMapperProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String mapAnalyzedInstructionsToString(Collection<RecipeDto.AnalyzedInstruction> instructions) {
        try {
            return objectMapper.writeValueAsString(instructions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing AnalyzedInstructions", e);
        }
    }

    public Collection<RecipeDto.AnalyzedInstruction> mapStringToAnalyzedInstructions(String data) {
        try {
            TypeReference<Collection<RecipeDto.AnalyzedInstruction>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(data, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing AnalyzedInstructions", e);
        }
    }

}
