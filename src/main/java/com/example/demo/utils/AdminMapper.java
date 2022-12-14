package com.example.demo.utils;

import com.example.demo.model.Admin;
import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.model.dto.AdminDTO;
import com.example.demo.model.dto.CustomerDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminMapper {

    public Admin convertDTOtoAdmin(AdminDTO adminDTO){
        Admin admin = (Admin) User.builder().build();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(new BCryptPasswordEncoder().encode(adminDTO.getPassword()));
        return admin;
    }

}
