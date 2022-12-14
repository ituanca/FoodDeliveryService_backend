package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, Integer> {

//    @Query("{category:'?0'}")
    Optional<Category> findByCategory(String category);
}
