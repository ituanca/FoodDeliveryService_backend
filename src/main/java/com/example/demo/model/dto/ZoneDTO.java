package com.example.demo.model.dto;

import com.example.demo.model.Restaurant;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ZoneDTO {

    private String name;

    private List<Restaurant> restaurants;

}
