package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController // the class is ready for use by Spring MVC to handle web requests (the class is a controller)
@RequestMapping(path="/customer")
public class CustomerController { // when invoked from a browser the method returns pure text

    // customerService will be autowired for us (it will be magically instantiated, then injected into the constructor)
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/index")
    public List<User> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id){
        log.info("CustomerController:findById " + " id sent from frontend: " + id);
        return Optional.ofNullable(customerService.findById(String.valueOf(id)));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        log.info("CustomerController:findByUsername " + " username sent from frontend: " + username);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByUsername(username));
    }

    @PostMapping(path = "/createAndValidate", consumes = {"application/json"})
    public String createAndValidate(@RequestBody CustomerDTO customer){
        log.info("CustomerController:createAndValidate " + " name entered: " + customer.getName());
        log.info("CustomerController:createAndValidate " + " email entered: " + customer.getEmail());
        log.info("CustomerController:createAndValidate " + " username entered: " + customer.getUsername());
        return customerService.createAndValidate(customer);
    }

    @GetMapping("/login")
    public String login(@Param("username") String username, @Param("password") String password){
        log.info("CustomerController:login " + " username entered: " + username);
        return customerService.login(username, password);
    }

}

/*
@RestController combines @Controller and @ResponseBody, two annotations
that result in web requests returning data rather than a view
 */