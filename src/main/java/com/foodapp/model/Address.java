package com.foodapp.model;

import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Nullable
    private Long id;

    @Nullable
    private String streetAddress;

    @Nullable
    private String city;

    @Nullable
    private String stateProvinces;

    @Nullable
    private Long postalCode;

    @Nullable
    private String country;

}
