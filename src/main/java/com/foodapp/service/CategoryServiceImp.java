package com.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.model.Category;
import com.foodapp.model.Resturant;
import com.foodapp.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    public ResturantService resturantService;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception{
        Resturant resturant=resturantService.getResturantByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setResturant(resturant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByResturantId(Long id) throws Exception {
        return categoryRepository.findByResturantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory=categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found ......");
        }
        return optionalCategory.get();
    }

}
