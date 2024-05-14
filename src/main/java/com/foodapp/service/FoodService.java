package com.foodapp.service;



import java.util.List;

import com.foodapp.model.Category;
import com.foodapp.model.Food;
import com.foodapp.model.Resturant;
import com.request.CreateFoodRequest;

public interface FoodService {

    public Food createFood(CreateFoodRequest req,Category category,Resturant resturant);

    void deleteFood(Long foodId) throws Exception;
    
    public List<Food> getResturantsFood(Long resturantId,
                                        boolean isVegetarian,   
                                        boolean isNonveg,
                                        boolean isSeasonal,
                                        String foodCategory);
    
    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;

    public List<Food> allFoods();
                                        
    
    
} 