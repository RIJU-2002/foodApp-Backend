package com.foodapp.service;

import java.util.List;

import com.foodapp.model.IngredientCategory;
import com.foodapp.model.IngredientsItem;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name,Long resturantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByResturantId(Long id) throws Exception;

    public IngredientsItem createIngredientsItem(Long resturantId,String ingredientName,Long categoryId) throws Exception;

    public List<IngredientsItem> findResturantsIngredients(Long resturantId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;
    
}