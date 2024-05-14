package com.foodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodapp.model.Food;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByResturantId(Long resturantId);

    List<Food> searchByResturantId(Long resturantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodcategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);

    @Query("SELECT f from Food f")
    List<Food> allFood();
}
