package com.foodapp.service;

import java.util.List;

import com.foodapp.model.Category;

public interface CategoryService {

    public Category createCategory(String name,Long userId) throws Exception;

    public List<Category> findCategoryByResturantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
