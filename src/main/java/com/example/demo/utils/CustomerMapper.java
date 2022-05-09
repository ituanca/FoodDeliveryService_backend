package com.example.demo.utils;

import com.example.demo.model.Customer;
import com.example.demo.model.dto.CustomerDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerMapper {

    public Customer convertDTOtoCustomer(CustomerDTO customerDTO){
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .build();

        customer.setEmail(customerDTO.getEmail());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));

        return customer;
    }

//    public CustomerDTO convertCustomerToDTO(Customer customer){
//        CustomerDTO customerDTO = CustomerDTO.builder()
//                .name(customer.getName())
//                .email(customer.getEmail())
//                .username(customer.getUsername())
//                .password(new BCryptPasswordEncoder().encode(customer.getPassword()))
//                .build();
//        return customerDTO;
//    }

}
