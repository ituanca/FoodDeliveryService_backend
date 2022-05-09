package com.example.demo.model.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDTO {

    String username;

    String password;

    String name;

    String email;
}
