package com.dzeinz.food_tracker.controller;

import com.dzeinz.food_tracker.entity.Cart;
import com.dzeinz.food_tracker.entity.Ingredient;
import com.dzeinz.food_tracker.service.CartService;
import com.dzeinz.food_tracker.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = {"","/","/index"})
    public String getList (
            @RequestParam(value="search", required = false) String search,
            Model model) {

        try {
            List<Ingredient> ingredients = ingredientService.getIngredientsList(search);
            model.addAttribute("search",search);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("cart",cart);

        } catch (Exception e ) {
            return "redirect:error";
        }

        return "list";
    }

    @GetMapping(value="/cart")
    public String getCart(Model model){
        model.addAttribute("cart",cart);
        return "cart";
    }

    @PostMapping(value="/cart")
    public String addToCart (
            @ModelAttribute Ingredient ingredient,
            HttpServletRequest request,
            Model model
    ) {
        try {
            cartService.addToCart(cart, ingredient);
        } catch (Exception e) {
            model.addAttribute("cart",cart);
            model.addAttribute("ingredient",ingredient);
            return "unavailable";
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping(value="error")
    public String error(Model model){
        model.addAttribute("cart",cart);
        return "error";
    }
}
