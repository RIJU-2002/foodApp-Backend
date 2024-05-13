package com.foodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.IngredientsItem;;
public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {
    
    List<IngredientsItem> findByResturantId(Long id);
}
