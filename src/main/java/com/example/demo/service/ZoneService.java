package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    /**
     * Gets all the zones from the database
     * @return the list of all the zones from the database
     */
    public List<Zone> findAll(){
        List<Zone> list = zoneRepository.findAll();
        if(list.isEmpty()){
            log.warn("ZoneService:findAll " + " List of all zones is empty!");
        }else{
            log.info("ZoneService:findAll " + " All existent zones were found!");
        }
        return list;
    }

    /**
     * Gets the zone with the specified id
     * @param id the id which I want the zone that I am looking for to have
     * @return null if the id being sought cannot be found in the database or a Zone object otherwise
     */
    public Optional<Zone> findById(Integer id){
        Optional<Zone> zone = zoneRepository.findById(id);
        if(zone.isEmpty()){
            log.warn("ZoneService:findById " + " Zone with id " + id + " was not found!");
        }else{
            log.info("ZoneService:findById " + " Zone with id " + id + " was found!");
        }
        return zone;
    }

    /**
     * Gets the zone with the specified name
     * @param name the name which I want the zone that I am looking for to have
     * @return null if the name being sought cannot be found in the database or a Zone object otherwise
     */
    public Optional<Zone> findByName(String name){
        Optional<Zone> zone = zoneRepository.findByName(name);
        if(zone.isEmpty()){
            log.warn("ZoneService:findByName " + " Zone with name " + name + " was not found!");
        }else{
            log.info("ZoneService:findByName " + " Zone with name " + name + " was found!");
        }
        return zone;
    }

}