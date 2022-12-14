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
@Builder
@Document("food")
public class Food {

    @Id
    String id;

    String food;

    String listOfIngredients;

    Double price;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name="idCategory")
    @DocumentReference
    private Category category;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="food_orderDetails",
//            joinColumns = @JoinColumn(name="idFood"),
//            inverseJoinColumns = @JoinColumn(name="idOrderDetails"))
    private List<OrderDetails> orderDetails;

    @DocumentReference
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
