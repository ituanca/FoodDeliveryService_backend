package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Zone;
import com.example.demo.model.dto.RestaurantDTO;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ZoneServiceTest {

    @InjectMocks
    private ZoneService zoneService;

    @Mock
    private ZoneRepository zoneRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Zone> expectedList = new ArrayList<>(List.of(dummy.getZ1(), dummy.getZ2(), dummy.getZ3()));
        when(zoneRepository.findAll()).thenReturn(expectedList);
        List<Zone> list = zoneService.findAll();

        assertEquals(3, list.size());
        verify(zoneRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest(){
        when(zoneRepository.findById(168)).thenReturn(Optional.of(dummy.getExpectedZone()));
        Zone zone = zoneService.findById(168).orElse(null);
        if(zone != null){
            assertEquals(dummy.getExpectedZone().getId(), zone.getId());
            assertEquals(dummy.getExpectedZone().getName(), zone.getName());
            assertEquals(dummy.getExpectedZone().getRestaurants(), zone.getRestaurants());
        }
    }

    @Test
    public void findByNameTest(){
        when(zoneRepository.findByName("Zorilor")).thenReturn(Optional.of(dummy.getExpectedZone()));
        Zone zone = zoneService.findByName("Zorilor").orElse(null);
        if(zone != null){
            assertEquals(dummy.getExpectedZone().getId(), zone.getId());
            assertEquals(dummy.getExpectedZone().getName(), zone.getName());
            assertEquals(dummy.getExpectedZone().getRestaurants(), zone.getRestaurants());
        }
    }

}
