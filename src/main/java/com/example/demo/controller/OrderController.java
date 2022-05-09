package com.example.demo.controller;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.service.OrderService;
import com.example.demo.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping(path="/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/create", consumes = {"application/json"})
    public Boolean createOrder(@RequestBody OrderDTO orderDTO ) throws MessagingException {
        log.info("OrderController:createOrder " + " customer's username: " + orderDTO.getCustomer().getUsername());
        log.info("OrderController:createOrder " + " restaurant name: " + orderDTO.getRestaurant());
        log.info("OrderController:createOrder " + " special details: " + orderDTO.getDetails());
        log.info("OrderController:createOrder " + " delivery address: " + orderDTO.getDeliveryAddress());
        log.info("OrderController:createOrder " + " ordered items: " + orderDTO.getItems());
        log.info("OrderController:createOrder " + " total price of the order: " + orderDTO.getTotalPrice());
        return orderService.createOrder(orderDTO);
    }

}