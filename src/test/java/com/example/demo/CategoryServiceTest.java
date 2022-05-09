package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    Dummy dummy = new Dummy();

    @Test
    public void findAllTest(){

        List<Category> expectedList = new ArrayList<>(List.of(dummy.getCategory1(), dummy.getCategory2(), dummy.getCategory3()));

        when(categoryRepository.findAll()).thenReturn(expectedList);
        List<CategoryDTO> list = categoryService.findAll();

        assertEquals(3, list.size());
        verify(categoryRepository, times(1)).findAll();
    }

}
