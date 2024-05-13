package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem,Long> {

    
}
