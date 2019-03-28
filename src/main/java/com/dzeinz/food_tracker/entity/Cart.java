package com.dzeinz.food_tracker.entity;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection = "carts")
@Scope("session")
public class Cart {

    @Id
    private String id;
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinProperty(name="ingredients")
    private List<Ingredient> ingredients;

    public Integer totalCalories(){
        Integer totalCals = 0;
        for (int i = 0; i < this.ingredients.size(); i++){
            totalCals += this.ingredients.get(i).getCalories();
        }
        return totalCals;
    }

    public void addIngredient(Ingredient ingredient){
        if(this.ingredients == null){
            this.ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}