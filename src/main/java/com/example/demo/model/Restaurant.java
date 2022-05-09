package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="restaurant_zone",
    joinColumns = @JoinColumn(name="idRestaurant"),
    inverseJoinColumns = @JoinColumn(name="idZone"))
    private List<Zone> zones;

    @OneToOne(cascade = CascadeType.ALL) // FK
    @JoinColumn(name="idMenu")
    private Menu menu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdmin")  // FK
    private Admin admin;

    public Restaurant(Integer id, String name, String address, List<Order> orders, List<Zone> zones) {
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

    public Restaurant(String name,String address, List<Zone> zones, Admin admin) {
        this.name = name;
        this.address = address;
        this.zones = zones;
        this.admin = admin;
    }

    @Builder
    public Restaurant(String name,String address, List<Zone> zones, Menu menu, Admin admin) {
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
