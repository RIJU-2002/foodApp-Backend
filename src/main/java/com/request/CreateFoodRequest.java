package com.request;

import java.util.List;

import com.foodapp.model.Category;
import com.foodapp.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long resturantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;
    
}
