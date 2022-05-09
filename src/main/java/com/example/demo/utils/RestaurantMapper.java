package com.example.demo.utils;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Zone;
import com.example.demo.model.dto.RestaurantDTO;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {

    public RestaurantDTO convertRestaurantToDTO(Restaurant restaurant){
        List<Zone> zones = restaurant.getZones();
        List<String> zonesString = new ArrayList<>();
        for(Zone z: zones){
            zonesString.add(z.toString());
        }
        return RestaurantDTO.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .admin(restaurant.getAdmin())
                .zones(zonesString)
                .build();
    }

}
