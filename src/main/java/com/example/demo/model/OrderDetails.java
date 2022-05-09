package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name="idOrder")
    private Order order_;

    @ManyToMany(mappedBy = "orderDetails")
    private List<Food> listOfFood;

    @Column(name="quantity")
    private Integer quantity;

    public OrderDetails() {
    }

    public OrderDetails(Integer id, Order order_, List<Food> listOfFood, Integer quantity) {
        this.id = id;
        this.order_ = order_;
        this.listOfFood = listOfFood;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order_;
    }

    public void setOrder(Order order_) {
        this.order_ = order_;
    }

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    public void setListOfFood(List<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
