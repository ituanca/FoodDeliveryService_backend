package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Restaurant;
import com.example.demo.model.dto.RestaurantDTO;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private ZoneRepository zoneRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Restaurant> expectedList = new ArrayList<>(List.of(dummy.getRestaurant1(), dummy.getRestaurant2(), dummy.getRestaurant3()));

        when(restaurantRepository.findAll()).thenReturn(expectedList);
        List<RestaurantDTO> list = restaurantService.findAll();

        assertEquals(3, list.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest(){
        when(restaurantRepository.findById(423)).thenReturn(Optional.of(dummy.getExpectedRestaurant()));
        Restaurant restaurant = restaurantService.findById(423).orElse(null);
        if(restaurant != null){
            assertEquals(dummy.getExpectedRestaurant().getName(), restaurant.getName());
            assertEquals(dummy.getExpectedRestaurant().getAddress(), restaurant.getAddress());
            assertEquals(dummy.getExpectedRestaurant().getZones(), restaurant.getZones());
        }
    }

    @Test
    public void findByNameTest(){
        when(restaurantRepository.findByName("Crinul Alb")).thenReturn(Optional.of(dummy.getExpectedRestaurant()));
        Restaurant restaurant = restaurantService.findByName("Crinul Alb").orElse(null);
        if(restaurant != null){
            assertEquals(dummy.getExpectedRestaurant().getName(), restaurant.getName());
            assertEquals(dummy.getExpectedRestaurant().getAddress(), restaurant.getAddress());
            assertEquals(dummy.getExpectedRestaurant().getZones(), restaurant.getZones());
        }
    }

    @Test
    public void findByAdminTest(){
        String adminUsername = "adminCrinulAlb";
        when(adminRepository.findByUsername(adminUsername)).thenReturn(Optional.of(dummy.getAdminCrinulAlb()));
        when(restaurantRepository.findByAdmin(dummy.getAdminCrinulAlb())).thenReturn(Optional.of(dummy.getExpectedRestaurant()));
        String restaurantName = restaurantService.findByAdmin(adminUsername);
        assertEquals(dummy.getExpectedRestaurant().getName(), restaurantName);
    }

    @Test
    public void findMenuByAdminTest(){
        String adminUsername = "adminCrinulAlb";
        when(adminRepository.findByUsername(adminUsername)).thenReturn(Optional.of(dummy.getAdminCrinulAlb()));
        when(restaurantRepository.findByAdmin(dummy.getAdminCrinulAlb())).thenReturn(Optional.of(dummy.getExpectedRestaurant()));
        String menuId = restaurantService.findMenuByAdmin(adminUsername);
        assertEquals(dummy.getExpectedRestaurant().getMenu().getId().toString(), menuId);
    }

    @Test
    public void findMenuByRestaurantTest(){
        String restaurantName = "Crinul Alb";
        when(restaurantRepository.findByName(restaurantName)).thenReturn(Optional.of(dummy.getExpectedRestaurant()));
        String menuId = restaurantService.findMenuByRestaurant(restaurantName);
        assertEquals(dummy.getExpectedRestaurant().getMenu().getId().toString(), menuId);
    }

    @Test
    public void createRestaurantTest(){

    }

}
