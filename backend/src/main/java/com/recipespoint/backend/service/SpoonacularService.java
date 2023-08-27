package com.recipespoint.backend.service;

import com.recipespoint.backend.dto.RecipeDto;
import com.recipespoint.backend.dto.RecipeQuery;
import com.recipespoint.backend.dto.SpoonacularResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SpoonacularService {

    private final RestTemplate restTemplate;

    @Value("${spoonacular.api.url}")
    private String baseUrl;

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Autowired
    public SpoonacularService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RecipeDto> getRecipes(RecipeQuery query) {
        String url = buildUrl(query);
        log.info("Querying Spoonacular for recipes with query: {}", query);
        SpoonacularResponse response = restTemplate.getForObject(url, SpoonacularResponse.class);
        return Optional.ofNullable(response)
                .map(SpoonacularResponse::results)
                .orElse(List.of());
    }

    private String buildUrl(RecipeQuery query) {
        String url = baseUrl + "/recipes/complexSearch?addRecipeInformation=true";
        url += "&apiKey=" + apiKey;
        url += "&" + query.getAsParams();
        return url;
    }
}
