package com.dzeinz.food_tracker.service;

import com.dzeinz.food_tracker.entity.Ingredient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.dzeinz.food_tracker.config.UsdaConfig.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Override
    public List<Ingredient> getIngredientsList(String search) throws Exception {

        // build the URL
        URIBuilder b = new URIBuilder(USDA_SEARCH_URL);
        b.addParameter("format", USDA_FORMAT);
        b.addParameter("max", USDA_SEARCH_MAX);
        b.addParameter("api_key", USDA_API_KEY);
        b.addParameter("q", search);
        String url = b.build().toString();

        // send API request
        RestTemplate restTemplate = new RestTemplate();
        String apiResponse = restTemplate.getForObject(url, String.class);

        // parse response to List<Ingredient>
        List<Ingredient> ingredients;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode itemNode = (ArrayNode) mapper.readTree(apiResponse)
                    .path("list")
                    .path("item");
            ingredients = Arrays.asList(mapper.treeToValue(itemNode, Ingredient[].class));
        } catch (Exception e) {
            return null;
        }

        // return List<Ingredient>
        return ingredients;
    }

    @Override
    public Integer getIngredientCals(Ingredient ingredient) throws Exception {
        // build the URL
        URIBuilder b = new URIBuilder(USDA_NUTRIENTS_URL);
        b.addParameter("format", USDA_FORMAT);
        b.addParameter("api_key", USDA_API_KEY);
        b.addParameter("nutrients", USDA_NUTRIENTS_KCAL);
        b.addParameter("ndbno", ingredient.getExternalId());
        String url = b.build().toString();

        // send API request
        RestTemplate restTemplate = new RestTemplate();
        String apiResponse = restTemplate.getForObject(url, String.class);

        JsonNode itemNode = new ObjectMapper().readTree(apiResponse)
                .path("report")
                .path("foods")
                .path(0)
                .path("nutrients")
                .path(0);

        return itemNode.get("value").asInt();
    }
}
