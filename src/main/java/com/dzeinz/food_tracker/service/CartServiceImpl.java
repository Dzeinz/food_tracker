package com.dzeinz.food_tracker.service;

import com.dzeinz.food_tracker.entity.Cart;
import com.dzeinz.food_tracker.entity.Ingredient;
import com.dzeinz.food_tracker.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public void addToCart(Cart cart, Ingredient ingredient) throws Exception {
        ingredient.setCalories(ingredientService.getIngredientCals(ingredient));
        cart.addIngredient(ingredient);
        cartRepository.save(cart);
    }

}