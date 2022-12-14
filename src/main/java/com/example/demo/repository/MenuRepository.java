package com.example.demo.repository;

import com.example.demo.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MenuRepository extends MongoRepository<Menu, String> {
    Menu getById(String id);
}
