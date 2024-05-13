package com.foodapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

    public List<Category> findByResturantId(Long id);

} 