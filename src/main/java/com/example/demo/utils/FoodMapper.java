package com.example.demo.utils;

import com.example.demo.model.Food;
import com.example.demo.model.dto.FoodDTO;

public class FoodMapper {

    public FoodDTO convertFoodToDTO(Food food){
        return FoodDTO.builder()
                .food(food.getFood())
                .listOfIngredients(food.getListOfIngredients())
                .price(food.getPrice().toString())
                .category(food.getCategory())
                .restaurant(food.getMenu().getRestaurant().getName())
                .build();
    }

}
