package com.example.demo.model.dto;

import com.example.demo.model.Admin;
import com.example.demo.model.Menu;
import com.example.demo.model.Zone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private String name;

    private String address;

    private List<String> zones;

    private Admin admin;

}
