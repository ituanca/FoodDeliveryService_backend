package com.example.demo.model.dto;

import com.example.demo.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FoodDTO {

    String food;

    String listOfIngredients;

    String price;

    Category category;

    String restaurant;

}
