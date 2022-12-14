package com.example.demo.repository;

import com.example.demo.model.Admin;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant,String> {

    @Query("{name:'?0'}")
    Optional<Restaurant> findByName(String name);

    Optional<Restaurant> findByAdmin(User admin);
}
