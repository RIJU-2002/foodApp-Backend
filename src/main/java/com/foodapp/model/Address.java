package com.foodapp.model;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
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

    @Column(nullable = true)
    private String streetAddress;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String stateProvinces;

    @Column(nullable = true)
    private Long postalCode;

    @Column(nullable = true)
    private String country;

}
