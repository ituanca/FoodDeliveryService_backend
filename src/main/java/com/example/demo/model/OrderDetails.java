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
@Document("orderdetails")
public class OrderDetails {
    @Id
    Integer id;

//    @ManyToOne
//    @JoinColumn(name="idOrder")
    private Order order_;

//    @ManyToMany(mappedBy = "orderDetails")
    private List<Food> listOfFood;

    private Integer quantity;

}
