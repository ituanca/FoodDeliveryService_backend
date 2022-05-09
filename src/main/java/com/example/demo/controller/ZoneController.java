package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.model.Zone;
import com.example.demo.model.dto.ZoneDTO;
import com.example.demo.service.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path="/zone")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping("/index")
    public List<Zone> findAll(){
        return zoneService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Zone> findById(@PathVariable Integer id){
        log.info("ZoneController:findById " + " id sent from frontend: " + id);
        return zoneService.findById(id);
    }

    @GetMapping("/search/{name}")
    public Optional<Zone> findByName(@PathVariable String name){
        log.info("ZoneController:findByName " + " name sent from frontend: " + name);
        return zoneService.findByName(name);
    }


}