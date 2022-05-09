package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Restaurant;
import com.example.demo.model.dto.RestaurantDTO;
import com.example.demo.service.RestaurantService;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path="/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/index")
    public List<RestaurantDTO> findAll(){
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> findById(@PathVariable Integer id){
        log.info("RestaurantController:findById " + " id sent from frontend: " + id);
        return restaurantService.findById(id);
    }

    @GetMapping("/findByName/{name}")
    public Optional<Restaurant> findByName(@PathVariable String name){
        log.info("RestaurantController:findByName " + " name sent from frontend: " + name);
        return restaurantService.findByName(name);
    }

    @GetMapping("/findByAdmin")
    public String findByAdmin(@Param("admin") String admin){
        log.info("RestaurantController:findByAdmin " + " administrator's name sent from frontend: " + admin);
        return restaurantService.findByAdmin(admin);
    }

    @GetMapping("/findMenuByAdmin")
    public String findMenuByAdmin(@Param("admin") String admin){
        log.info("RestaurantController:findMenuByAdmin " + " administrator's name sent from frontend: " + admin);
        return restaurantService.findMenuByAdmin(admin);
    }

    @GetMapping("/findMenuByRestaurant")
    public String findMenuByRestaurant(@Param("name") String name){
        log.info("RestaurantController:findByAdmin " + " name of restaurant sent from frontend: " + name);
        return restaurantService.findMenuByRestaurant(name);
    }

    @GetMapping("/generatePdf")
    public Boolean generatePdf(@Param("menu") String menu) throws IOException {
        log.info("RestaurantController:generatePdf " + " menu id sent from frontend: " + menu);
        return restaurantService.generatePdf(menu);
    }

    @PostMapping(path = "/create", consumes = {"application/json"})     // post is used when we want to add more resources to our system
    public Boolean createRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        log.info("RestaurantController:add " + " name entered: " + restaurantDTO.getName());
        log.info("RestaurantController:add " + " administrator's username: " + restaurantDTO.getAdmin().getUsername());
        log.info("RestaurantController:add " + " address entered: " + restaurantDTO.getAddress());
        log.info("RestaurantController:add " + " zones selected: " + restaurantDTO.getZones());
        return restaurantService.createRestaurant(restaurantDTO);
    }

}