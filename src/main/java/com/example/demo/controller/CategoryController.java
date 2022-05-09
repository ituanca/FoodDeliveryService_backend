package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Category;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path="/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public List<CategoryDTO> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findById(@PathVariable Integer id){
        log.info("CategoryController:findById " + " id sent from frontend: " + id);
        return categoryService.findById(id);
    }

}