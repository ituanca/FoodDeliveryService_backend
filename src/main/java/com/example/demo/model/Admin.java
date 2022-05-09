package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Admin extends User{

    @JsonIgnore
    @OneToOne(mappedBy="admin")  // PK
    private Restaurant restaurant;

    public Admin(String email, String username, String password) {
        super(email, username, password);
    }

    public Admin(String email, String username, String password, Restaurant restaurant) {
        super(email, username, password);
        this.restaurant = restaurant;
    }

}
