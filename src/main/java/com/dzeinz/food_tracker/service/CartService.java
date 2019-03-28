package com.dzeinz.food_tracker.service;

import com.dzeinz.food_tracker.entity.Cart;
import com.dzeinz.food_tracker.entity.Ingredient;

public interface CartService {

    public void addToCart(Cart cart, Ingredient ingredient) throws Exception;

}