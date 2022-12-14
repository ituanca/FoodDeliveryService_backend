package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Restaurant;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.utils.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService {

    //Config config = new Config();

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    /**
     * Gets all the categories from the database
     * @return the list of all the categories as DTOs to be sent to the frontend
     */
    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        CategoryMapper categoryMapper = new CategoryMapper();
        for(Category c : categories){
            categoriesDTO.add(categoryMapper.convertCategoryToDTO(c));
        }
        log.info("CategoryService:findAll " + " Categories " + categoriesDTO + " successfully fetched from database!");
        return categoriesDTO;
    }

    /**
     * Gets the category with the specified id
     * @param id the id I want the category that I am looking for to have
     * @return either an object of Category class, or null in case the category with the specified id
     * does not exist in the database
     */
    public Optional<Category> findById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            log.warn("CategoryService:findById " + " Category with id " + id + " was not found!");
        }else{
            log.info("CategoryService:findById " + " Category with id " + id + " was found!");
        }
        return category;
    }

}