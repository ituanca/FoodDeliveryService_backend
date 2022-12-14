package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.sendemail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    private String createTextToSend(OrderDTO orderDTO){
        StringBuilder textToSend = new StringBuilder("<h2>Items ordered: </h2>");
        textToSend.append("<h3>Details: ").append(orderDTO.getCustomer().getUsername()).append(" </h3>");
        for(String foodItem: orderDTO.getItems()){
            textToSend.append("<h4>").append(foodItem).append(" </h4>");
        }
        textToSend.append("<h2>Total price: ").append(orderDTO.getTotalPrice().toString()).append(" </h2>");
        textToSend.append("<h3>Delivery address: ").append(orderDTO.getDeliveryAddress()).append(" </h3>");
        textToSend.append("<h4>Details: ").append(orderDTO.getDetails()).append(" </h4>");
        return textToSend.toString();
    }

    public Boolean createOrder(OrderDTO orderDTO) throws MessagingException {
        Restaurant restaurant = restaurantRepository.findByName(orderDTO.getRestaurant()).orElse(null);
        User customer = userRepository.findByUsername(orderDTO.getCustomer().getUsername()).orElse(null);

        if(restaurant != null){
            User admin = restaurant.getAdmin();
            if(admin!=null && customer!=null){
                EmailService emailService = new EmailService();
                //String textToBeSent = createTextToSend(orderDTO);
                String textToBeSent = "This is the text";
                emailService.sendEmail(textToBeSent);
                log.info("Coming back from sending email");
            }
        }
        return true;
    }

}