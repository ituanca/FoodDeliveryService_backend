package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Customer extends User{

    @Column(name="name")
    private String name;

    @OneToOne(mappedBy = "customer") // PK
    private Order order;

    public Customer(String username, String password, String name, String email, Order order) {
        super(email, username, password);
        this.name = name;
        this.order = order;
    }

    public Customer(String username, String password, String name, String email) {
        super(email, username, password);
        this.name = name;
    }

}
