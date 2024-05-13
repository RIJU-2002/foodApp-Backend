package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String username);
    
}
