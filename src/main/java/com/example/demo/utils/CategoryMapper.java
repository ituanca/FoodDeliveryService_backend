package com.example.demo.utils;

import com.example.demo.model.Category;
import com.example.demo.model.dto.CategoryDTO;

public class CategoryMapper {

    public CategoryDTO convertCategoryToDTO(Category category){
        return CategoryDTO.builder().category(category.getCategory()).build();
    }

}
