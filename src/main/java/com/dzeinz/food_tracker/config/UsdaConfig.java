package com.dzeinz.food_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsdaConfig {

    public static String USDA_SEARCH_URL;
    public static String USDA_SEARCH_MAX;
    public static String USDA_FORMAT;
    public static String USDA_API_KEY;
    public static String USDA_NUTRIENTS_URL;
    public static String USDA_NUTRIENTS_KCAL;

    @Autowired
    public UsdaConfig(
            @Value("${usda.api.USDA_SEARCH_URL}") String USDA_SEARCH_URL,
            @Value("${usda.api.USDA_SEARCH_MAX}") String USDA_SEARCH_MAX,
            @Value("${usda.api.USDA_FORMAT}") String USDA_FORMAT,
            @Value("${usda.api.USDA_API_KEY}") String USDA_API_KEY,
            @Value("${usda.api.USDA_NUTRIENTS_URL}") String USDA_NUTRIENTS_URL,
            @Value("${usda.api.USDA_NUTRIENTS_KCAL}") String USDA_NUTRIENTS_KCAL
    ) {
        this.USDA_SEARCH_URL = USDA_SEARCH_URL;
        this.USDA_SEARCH_MAX = USDA_SEARCH_MAX;
        this.USDA_FORMAT = USDA_FORMAT;
        this.USDA_API_KEY = USDA_API_KEY;
        this.USDA_NUTRIENTS_URL = USDA_NUTRIENTS_URL;
        this.USDA_NUTRIENTS_KCAL = USDA_NUTRIENTS_KCAL;
    }
}
