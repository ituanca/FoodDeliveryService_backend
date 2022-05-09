package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Food;
import com.example.demo.model.Restaurant;
import com.example.demo.model.dto.FoodDTO;
import com.example.demo.model.dto.RestaurantDTO;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;
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
public class FoodServiceTest {

    @InjectMocks
    private FoodService foodService;

    @Mock
    private FoodRepository foodRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Food> expectedList = new ArrayList<>(List.of(dummy.getFood11(), dummy.getFood22(), dummy.getFood33()));

        when(foodRepository.findAll()).thenReturn(expectedList);
        List<Food> list = foodService.findAll();

        assertEquals(3, list.size());
        verify(foodRepository, times(1)).findAll();
    }

    @Test
    public void findByMenu(){
        int menuId = 1;
        List<Food> expectedList = new ArrayList<>(List.of(dummy.getFood11(), dummy.getFood22(), dummy.getFood33(), dummy.getFood44()));
        when(foodRepository.findAll()).thenReturn(expectedList);
        List<Food> expectedListOfFoodFromMenu = expectedList.stream().filter(f -> f.getMenu().getId().equals(menuId)).toList();
        List<Food> listOfFoodFromMenu = foodService.findByMenu(Integer.toString(menuId));
        if(!listOfFoodFromMenu.isEmpty()){
            assertEquals(3, listOfFoodFromMenu.size());
            assertEquals(expectedListOfFoodFromMenu, listOfFoodFromMenu);
        }
    }

}
