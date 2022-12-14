package com.example.demo.repository;

import com.example.demo.model.Admin;
import com.example.demo.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends MongoRepository<Zone, String> {
    @Query("{name:'?0'}")
    Optional<Zone> findByName(String name);
}
