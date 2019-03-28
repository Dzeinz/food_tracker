package com.dzeinz.food_tracker.controller;

import com.dzeinz.food_tracker.entity.Cart;
import com.dzeinz.food_tracker.entity.Ingredient;
import com.dzeinz.food_tracker.service.CartService;
import com.dzeinz.food_tracker.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Scope("request")
public class WebController {

    @Autowired
    private Cart cart;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = {"","/","/index"})
    public String getList (
            @RequestParam(value="search", required = false) String search,
            Model model) {

        try {
            List<Ingredient> ingredients = ingredientService.getIngredientsList(search);
            model.addAttribute("search",search);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("cart",cart);

        } catch (Exception e ) {
            return "error";
        }

        return "list";
    }
}
