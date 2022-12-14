package com.example.demo.repository;

import com.example.demo.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
    Food findByFood(String food);
}
