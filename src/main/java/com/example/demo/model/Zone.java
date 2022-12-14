package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("zone")
public class Zone {

    @Id
    private String id;

    private String name;

    public Zone(String zone, List<Restaurant> restaurants) {
        this.name = zone;
    }

    public Zone(String zone) {
        this.name = zone;
    }

}
