package com.example.demo.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private CustomerDTO customer;

    private List<String> items;

    private Double totalPrice;

    private String deliveryAddress;

    private String details;

    private String restaurant;

}
