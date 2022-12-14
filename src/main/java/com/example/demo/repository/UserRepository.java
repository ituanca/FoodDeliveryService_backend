package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{username:'?0'}")
    Optional<User> findByUsername(String username);

    @Query("{email:'?0'}")
    Optional<User> findByEmail(String email);
}
