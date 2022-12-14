package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.dto.RestaurantDTO;
import com.example.demo.repository.*;
import com.example.demo.utils.PDFGeneratorMenu;
import com.example.demo.utils.RestaurantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantService {

    RestaurantRepository restaurantRepository;
    ZoneRepository zoneRepository;
    UserRepository userRepository;
    MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ZoneRepository zoneRepository, UserRepository userRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.zoneRepository = zoneRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    /**
     * Gets all the restaurants from the database
     * @return a list of RestaurantDTO objects to be sent to the frontend
     */
    public List<RestaurantDTO> findAll(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        List<RestaurantDTO> restaurantsDTO = new ArrayList<>();
        for(Restaurant r: restaurants){
            restaurantsDTO.add(restaurantMapper.convertRestaurantToDTO(r));
        }
        log.info("RestaurantService:findAll " + " All existent restaurants were successfully fetched from database!");
        return restaurantsDTO;
    }

    /**
     * Gets the restaurant with the specified id
     * @param id the id that I want the restaurant being sought to have
     * @return either an object of the Restaurant class, or null in case the restaurant with the specified id
     * does not exist in the database
     */
    public Optional<Restaurant> findById(String id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            log.warn("RestaurantService:findById " + " Restaurant with id " + id + " was not found!");
        }else{
            log.info("RestaurantService:findById " + " Restaurant with id " + id + " was found!");
        }
        return restaurant;
    }

    /**
     * Gets the restaurant with the specified name
     * @param name the name that I want the restaurant being sought to have
     * @return either an object of the Restaurant class, or null in case the restaurant with the specified name
     * does not exist in the database
     */
    public Optional<Restaurant> findByName(String name){
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if(restaurant.isEmpty()){
            log.warn("RestaurantService:findByName " + " Restaurant with name " + name + " was not found!");
        }else{
            log.info("RestaurantService:findByName " + " Restaurant with name " + name + " was found!");
        }
        return restaurant;
    }

    /**
     * Gets the restaurant administrated by the specified user
     * @param adminString the name that I want the admin of the restaurant being sought to have
     * @return a string which represents either the name of the found restaurant or the empty string in case
     * there is not found a restaurant in the database associated to the specified name
     */
    public String findByAdmin(String adminString){
        User admin = userRepository.findByUsername(adminString).orElse(null);
        Restaurant restaurant = restaurantRepository.findByAdmin(admin).orElse(null);
        if(restaurant != null){
            log.info("RestaurantService:findByAdmin " + " Restaurant associated to admin " + adminString + " was found!");
            return restaurant.getName();
        }
        log.warn("RestaurantService:findByAdmin " + " Restaurant associated to admin " + adminString + " was not found!");
        return "";
    }

    /**
     * Gets the menu which is indirectly associated to the specified admin
     * @param adminString the name that I want the admin of the restaurant with the menu being sought to have
     * @return a string which represents either the id of the found menu or the empty string in case there is not
     * found a menu in the database associated to the specified name
     */
    public String findMenuByAdmin(String adminString){
        User admin = userRepository.findByUsername(adminString).orElse(null);
        Restaurant restaurant = restaurantRepository.findByAdmin(admin).orElse(null);
        if(restaurant!=null){
            log.info("RestaurantService:findMenuByAdmin " + " Menu associated to admin " + adminString + " was found!");
            return restaurant.getMenu().getId();
        }
        log.warn("RestaurantService:findMenuByAdmin " + " Menu associated to admin " + adminString + " not found!");
        return "";
    }

    /**
     * Gets the menu associated to the specified restaurant
     * @param name the name of the restaurant associated to the menu being sought
     * @return a string which is empty in case no menu was found and the id of the menu otherwise
     */
    public String findMenuByRestaurant(String name){
        Restaurant restaurant = restaurantRepository.findByName(name).orElse(null);
        if(restaurant!=null){
            if(restaurant.getMenu() != null){
                log.info("RestaurantService:findMenuByRestaurant " + " Menu associated to restaurant " + name + " was found!");
                return restaurant.getMenu().getId();
            }
        }
        log.warn("RestaurantService:findMenuByRestaurant " + " Menu associated to restaurant " + name + " not found!");
        return "";
    }

    /**
     * Generates a PDF with the menu of the restaurant administrated by the admin who is currently logged in
     * @param menu the id of the menu which has to be exported as PDF
     * @return a boolean which is always true, showing that the PDF was successfully generated
     * @throws IOException throws an exception in case it appears an error when trying to create the PDF file
     */
    public Boolean generatePdf(String menu) throws IOException {
        Menu menuObject = menuRepository.getById(menu);

        PDFGeneratorMenu generatorMenu = new PDFGeneratorMenu();
        generatorMenu.setMenu(menuObject);
        generatorMenu.generate();
        log.info("RestaurantService:generatePDF " + " PDF fo menu  " + menu + " was generated!");
        return true;
    }

    /**
     * Inserts the restaurant into the database
     * @param restaurantDTO the DTO object received from th frontend
     * @return a boolean which is true if the data was successfully saved into the database and false otherwise
     */
    public Boolean createRestaurant(RestaurantDTO restaurantDTO) {

        List <String> deliveryZonesString = new ArrayList<>(restaurantDTO.getZones());
        List <Zone> deliveryZones = new ArrayList<>();

        for(String zoneId : deliveryZonesString){
            Zone zone =  zoneRepository.findById(zoneId).orElse(null);
            deliveryZones.add(zone);
        }

        Menu menu = new Menu();
        menuRepository.save(menu);

        User admin = User.builder().build();
        admin.setEmail(restaurantDTO.getAdmin().getEmail());
        admin.setUsername(restaurantDTO.getAdmin().getUsername());
        admin.setPassword(new BCryptPasswordEncoder().encode(restaurantDTO.getAdmin().getPassword()));
        admin.setType("Admin");
        userRepository.save(admin);
        log.info("RestaurantService:createRestaurant " + " Admin  " + admin.getUsername() + " created!");

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .zones(deliveryZones)
                .menu(menu)
                .admin(admin)
                .build();

        if(findByName(restaurant.getName()).isPresent()){
            log.warn("RestaurantService:createRestaurant " + " Name " + restaurant.getName() + " already exists!");
            return false;
        }
        restaurantRepository.save(restaurant);
        log.info("RestaurantService:createRestaurant " + " Restaurant created!");
        return true;
    }
}