package com.example.demo.repository;

import com.example.demo.model.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, Integer> {
}
