package com.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.model.Address;

public interface AddressRespository extends JpaRepository<Address,Long> {

}
