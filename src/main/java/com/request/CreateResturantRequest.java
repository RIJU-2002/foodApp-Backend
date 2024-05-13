package com.request;

import java.util.List;

import com.foodapp.model.Address;
import com.foodapp.model.ContactInformation;

import lombok.Data;

@Data
public class CreateResturantRequest {

    private Long id;

    private String name;

    private String description;

    private String cuisineType;

    private Address address;

    private ContactInformation contactInformation;

    private String openingHours;

    private List<String> images;

}
