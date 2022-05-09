package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.model.Restaurant;
import com.example.demo.model.dto.FoodDTO;
import com.example.demo.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/index")
    public List<Food> findAll(){
        return foodService.findAll();
    }

    @GetMapping("/findByMenu")
    public List<Food> findByMenu(@Param("menu") String menu){
        log.info("FoodController:findByMenu " + " menu sent from frontend: " + menu);
        return foodService.findByMenu(menu);
    }

    @PostMapping(path = "/add", consumes = {"application/json"})     // post is used when we want to add more resources to our system
    public String add(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        log.info("FoodController:add " + " name entered: " + foodDTO.getFood());
        log.info("FoodController:add " + " price entered: " + foodDTO.getPrice());
        log.info("FoodController:add " + " list of ingredients entered: " + foodDTO.getListOfIngredients());
        log.info("FoodController:add " + " name of restaurant: " + foodDTO.getRestaurant());
        return foodService.add(foodDTO);
    }

}