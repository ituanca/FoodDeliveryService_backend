package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // the field is auto-generated and it will not be provided by the user
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy="menu")
    private List<Food> listOfFood;

    @JsonIgnore
    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(Integer id, List<Food> listOfFood) {
        this.id = id;
        this.listOfFood = listOfFood;
    }

    public Menu(Integer id) {
        this.id = id;
    }

    public Menu(List<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }

}
