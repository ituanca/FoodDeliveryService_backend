package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("menu")
public class Menu {

    @Id
    private String id;

//    @JsonIgnore
//    @OneToMany(mappedBy="menu")
//    private List<Food> listOfFood;

//    @DocumentReference
//    private Restaurant restaurant;


}
