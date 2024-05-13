package com.foodapp.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;


    @JsonIgnore
    @ManyToOne
    private Resturant resturant;

    private Long totalAmount;

    private String orderStatus;

    private Date createAt;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<Orderitem> items=new ArrayList<>();

    //private Payment payment

    private int totalItems;

    private Long totalPrice;
}
