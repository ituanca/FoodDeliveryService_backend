package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name="food")
    String food;

    @Column(name="listOfIngredients")
    String listOfIngredients;

    @Column(name="price")
    Double price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idCategory")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="food_orderDetails",
            joinColumns = @JoinColumn(name="idFood"),
            inverseJoinColumns = @JoinColumn(name="idOrderDetails"))
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name="idMenu")
    private Menu menu;

    public Food(String food, String listOfIngredients, Double price, Category category, Menu menu, List<OrderDetails> orderDetails) {
        this.food = food;
        this.listOfIngredients = listOfIngredients;
        this.price = price;
        this.category = category;
        this.menu = menu;
        this.orderDetails = orderDetails;
    }

    public Food(String food, String listOfIngredients, Double price, Category category) {
        this.food = food;
        this.listOfIngredients = listOfIngredients;
        this.price = price;
        this.category=category;
    }

    public Food(String food, String listOfIngredients, Double price, Category category, Menu menu) {
        this.food = food;
        this.listOfIngredients = listOfIngredients;
        this.price = price;
        this.category=category;
        this.menu = menu;
    }

}
