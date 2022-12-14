package com.example.demo.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer extends User{

    private String name;

//    @OneToOne(mappedBy = "customer") // PK
//    private Order order;

    public Customer(String username, String password, String name, String email) {
        super(email, username, password);
        this.name = name;
    }

}
