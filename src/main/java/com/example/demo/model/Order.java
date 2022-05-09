package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // the field is auto-generated and it will not be provided by the user
    private Integer id;

    @OneToOne
    @JoinColumn(name="idCustomer") // FK
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name="idStatus")
    private Status status;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name="idRestaurant")
    private Restaurant restaurant;

//    @ManyToMany(mappedBy = "order")
//    private List<Food> listOfFood;

    @OneToMany(mappedBy="order_")
    private List<OrderDetails> orderDetails;

    public Order() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
