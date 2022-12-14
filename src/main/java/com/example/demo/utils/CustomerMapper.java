package com.example.demo.utils;

import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.model.dto.CustomerDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerMapper {

    public Customer convertDTOtoCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        return customer;
    }

}
