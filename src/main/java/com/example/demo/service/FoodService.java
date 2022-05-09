package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.dto.FoodDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.utils.FoodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Gets all the food items from the database
     * @return the list of all the food items from the database
     */
    public List<Food> findAll(){
        List<Food> list = foodRepository.findAll();
        if(list.isEmpty()){
            log.warn("FoodService:findAll " + " List of all food items is empty!");
        }else{
            log.info("FoodService:findAll " + " All existent food items were found!");
        }
        return list;
    }

    /**
     * Gets the list of food items belonging to the specified menu
     * @param menuString the id of the menu that I want the food item to be part of
     * @return a list of Food objects which belong to the specified menu
     */
    public List<Food> findByMenu(String menuString){
        Integer id = Integer.parseInt(menuString);
        List<Food> allFood = findAll();
        List<Food> allFoodFromMenu = allFood.stream().filter(f -> f.getMenu().getId().equals(id)).toList();
        if(allFoodFromMenu.isEmpty()){
            log.warn("FoodService:findByMenu " + " List of all food items from menu " + menuString + " is empty!");
        }else{
            log.info("FoodService:findByMenu " + " Food items from menu " + menuString + " were found!");
        }
        return allFoodFromMenu;
    }

    /**
     * Checks if the entered data is valid and if this is true the food item is inserted into the database
     * @param foodDTO the foodDTO object received form the frontend
     * @return a string which communicates if the data is valid, in which case the food item is inserted into
     * the database, or if the data is invalid and which one of the fields was improperly filled
     * @throws URISyntaxException throws an exception in case the price attribute of the DTO object which is a String
     * cannot be converted to a Double in order to be a valid attribute of the built Food object
     */
    public String add(FoodDTO foodDTO) throws URISyntaxException {

        Restaurant restaurant = restaurantRepository.findByName(foodDTO.getRestaurant()).orElse(null);
        Category category = categoryRepository.findByCategory(foodDTO.getCategory().getCategory()).orElse(null);

        if(!foodDTO.getFood().matches("^[a-zA-Z0-9\\-\\s]+$")){
            log.warn("FoodService:add " + " Name " + foodDTO.getFood() + " is not valid!");
            return "name_error";
        } else if(Double.parseDouble(foodDTO.getPrice()) < 1){
            log.warn("FoodService:add " + " Price " + foodDTO.getPrice() + " is not valid!");
            return "price_error";
        }

        if(restaurant != null){
            Food food = Food.builder()
                    .food(foodDTO.getFood())
                    .listOfIngredients(foodDTO.getListOfIngredients())
                    .price(Double.parseDouble(foodDTO.getPrice()))
                    .category(category)
                    .menu(restaurant.getMenu()).build();
            log.info("FoodService:add " + " Food " + food.getFood() + " successfully added to menu!");
            foodRepository.save(food);
            return "ok";
        }
        log.error("FoodService:add " + " Specified restaurant could not be found!");
        return "error";
    }

}