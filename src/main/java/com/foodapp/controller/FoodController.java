package com.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.model.Food;
import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.foodapp.service.FoodService;
import com.foodapp.service.ResturantService;
import com.foodapp.service.UserService;
import com.request.CreateFoodRequest;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResturantService resturantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                        @RequestHeader ("Authorization") String jwt) throws Exception{

        User user= userService.findUserByJwtToken(jwt);
        List<Food> foods=foodService.searchFood(name);

        return new ResponseEntity<>(foods,HttpStatus.OK);
    }

    @GetMapping("/resturant/{resturantId}")
    public ResponseEntity<List<Food>> getResturantFood(@RequestParam(required = false) boolean vegetarian,
                                                        @RequestParam(required = false) boolean seasonal,
                                                        @RequestParam(required = false) boolean nonveg,
                                                        @PathVariable Long resturantId,
                                                        @RequestParam(required = false) String foodCategory,
                                        @RequestHeader ("Authorization") String jwt) throws Exception{

        User user= userService.findUserByJwtToken(jwt);
        List<Food> foods=foodService.getResturantsFood(resturantId, vegetarian, nonveg, seasonal, foodCategory);

        return new ResponseEntity<>(foods,HttpStatus.OK);
    }
}
