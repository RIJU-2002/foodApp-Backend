package com.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.model.IngredientCategory;
import com.foodapp.model.IngredientsItem;
import com.foodapp.model.Resturant;
import com.foodapp.repository.IngredientCategoryRepository;
import com.foodapp.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService{

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private ResturantService resturantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long resturantId) throws Exception {
        Resturant resturant=resturantService.findResturantById(resturantId);

        IngredientCategory category=new IngredientCategory();
        category.setResturant(resturant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Ingredient category not found..");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByResturantId(Long id) throws Exception {
        resturantService.findResturantById(id);
        return ingredientCategoryRepository.findByResturantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long resturantId, String ingredientName, Long categoryId)
            throws Exception {
        
        Resturant resturant=resturantService.findResturantById(resturantId);
        IngredientCategory category=findIngredientCategoryById(categoryId);

        IngredientsItem item=new IngredientsItem();
        item.setName(ingredientName);
        item.setResturant(resturant);
        item.setCategory(category);

        IngredientsItem ingredient=ingredientItemRepository.save(item);
        category.getIngredient().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findResturantsIngredients(Long resturantId) throws Exception {
        return ingredientItemRepository.findByResturantId(resturantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientItem=ingredientItemRepository.findById(id);
        if(optionalIngredientItem.isEmpty()){
            throw new Exception("Ingredient not found......");
        }

        IngredientsItem ingredientsItem=optionalIngredientItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
