package com.dzeinz.food_tracker.service;

import com.dzeinz.food_tracker.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getIngredientsList(String search) throws Exception;

    public Integer getIngredientCals(Ingredient ingredient) throws Exception;

}