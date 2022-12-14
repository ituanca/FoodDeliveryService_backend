package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("order_")
public class Order {
    @Id
    private Integer id;

//    @OneToOne
//    @JoinColumn(name="idCustomer") // FK
//    private Customer customer;

//    @Enumerated(EnumType.STRING)
//    @Column(name="idStatus")
    private Status status;

    private String address;

//    @ManyToOne
//    @JoinColumn(name="idRestaurant")
    private Restaurant restaurant;

//    @ManyToMany(mappedBy = "order")
//    private List<Food> listOfFood;

//    @OneToMany(mappedBy="order_")
    private List<OrderDetails> orderDetails;

}
