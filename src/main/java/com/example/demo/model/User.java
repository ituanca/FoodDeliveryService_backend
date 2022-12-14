package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("user")
public class User {

    @Id
    String id;

    String email;

    String username;

    String password;

    String type;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User( String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User( String email, String username, String password, String type) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
