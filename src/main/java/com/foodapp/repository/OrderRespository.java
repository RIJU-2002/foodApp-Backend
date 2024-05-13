package com.foodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.Order;

public interface OrderRespository extends JpaRepository<Order,Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByResturantId(Long resturantId);
}
