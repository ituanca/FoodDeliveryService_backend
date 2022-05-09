package com.example.demo.repository;

import com.example.demo.model.Admin;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> findByAdmin(Admin admin);
}
