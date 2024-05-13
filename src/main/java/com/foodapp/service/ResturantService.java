package com.foodapp.service;

import java.util.List;

import com.foodapp.dto.ResturantDto;
import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.request.CreateResturantRequest;

public interface ResturantService {

    public Resturant createResturant(CreateResturantRequest req,User user);

    public Resturant updateResturant(Long resturantId,CreateResturantRequest updatedResturant) throws Exception;

    public void deleteResturant(Long resturantId) throws Exception;

    public List<Resturant> getAllResturant();

    public List<Resturant> searchResturant(String keyword);

    public Resturant findResturantById(Long id) throws Exception;

    public Resturant getResturantByUserId(Long userId) throws Exception;

    public ResturantDto addToFavorites(Long resturantId,User user) throws Exception;

    public Resturant updateResturantStatus(Long id) throws Exception;

}
