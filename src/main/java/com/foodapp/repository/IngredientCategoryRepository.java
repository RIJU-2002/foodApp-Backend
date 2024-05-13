package com.foodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long>{


    List<IngredientCategory> findByResturantId(Long id);
}
