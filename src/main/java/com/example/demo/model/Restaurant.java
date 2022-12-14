package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("restaurant")
public class Restaurant {

    @Id
    private String id;

    private String name;

    private String address;

//    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="restaurant_zone",
//    joinColumns = @JoinColumn(name="idRestaurant"),
//    inverseJoinColumns = @JoinColumn(name="idZone"))
    private List<Zone> zones;

//    @OneToOne(cascade = CascadeType.ALL) // FK
//    @JoinColumn(name="idMenu")
    @DocumentReference
    private Menu menu;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "idAdmin")  // FK
    @DocumentReference
    private User admin;

    public Restaurant(String id, String name, String address, List<Order> orders, List<Zone> zones) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.zones = zones;
    }

    public Restaurant(String name, String address, List<Order> orders, List<Zone> zones) {
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.zones = zones;
    }

    public Restaurant(String name,String address, List<Zone> zones) {
        this.name = name;
        this.address = address;
        this.zones = zones;
    }

    public Restaurant(String name,String address, List<Zone> zones, User admin) {
        this.name = name;
        this.address = address;
        this.zones = zones;
        this.admin = admin;
    }

    @Builder
    public Restaurant(String name,String address, List<Zone> zones, Menu menu, User admin) {
        this.name = name;
        this.address = address;
        this.zones = zones;
        this.menu = menu;
        this.admin = admin;
    }

    public Restaurant(String name,String address) {
        this.name = name;
        this.address = address;
    }

}
