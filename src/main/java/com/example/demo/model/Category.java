package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // the field is auto-generated and it will not be provided by the user
    private Integer id;

    @Column(name = "category")
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy="category")
    private List<Food> listOfFood;

    public Category(String category, List<Food> listOfFood, Menu menu) {
        this.category = category;
        this.listOfFood = listOfFood;
    }

    public Category(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Category(String category) {
        this.category = category;
    }

}
