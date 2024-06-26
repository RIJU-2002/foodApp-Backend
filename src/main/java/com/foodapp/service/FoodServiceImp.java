package com.foodapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.model.Category;
import com.foodapp.model.Food;
import com.foodapp.model.Resturant;
import com.foodapp.repository.FoodRepository;
import com.request.CreateFoodRequest;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Resturant resturant) {
        Food food=new Food();
        food.setFoodcategory(category);
        food.setResturant(resturant);
        food.setDesription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate((new Date()));

        Food savedFood=foodRepository.save(food);
        resturant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        
        Food food=findFoodById(foodId);
        food.setResturant(null);

        foodRepository.save(food);
    }

    @Override
    public List<Food> getResturantsFood(Long resturantId,
                                        boolean isVegetarian, 
                                        boolean isNonveg, 
                                        boolean isSeasonal,
                                        String foodCategory) {
        List<Food> foods=foodRepository.findByResturantId(resturantId);
        
        if(isVegetarian){
            foods=filterByVegetarian(foods,isVegetarian);
        }
        if(isNonveg){
            foods=filterByNonVeg(foods,isNonveg);
        }
        if(isSeasonal){
            foods=filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods=filterByCategory(foods,foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food->{
            if(food.getFoodcategory()!=null){
                return food.getFoodcategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }
    // private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
    //     return foods.stream()
    //         .filter(food -> food.getFoodcategory() != null && 
    //             food.getFoodcategory().getName().equals(foodCategory)) // Handle null with Optional
    //         .collect(Collectors.toList());
    //   }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food->food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food->food.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood=foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("Food not exist......");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> allFoods(){
        // TODO Auto-generated method stub
        return foodRepository.allFood();
    }

}
