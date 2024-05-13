package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    
}