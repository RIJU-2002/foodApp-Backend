package com.request;

import com.foodapp.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
    private Long resturantId;
    private Address deliveryAddress;
}
