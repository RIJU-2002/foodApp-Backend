package com.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.model.IngredientCategory;
import com.foodapp.model.IngredientsItem;
import com.foodapp.service.IngredientsService;
import com.request.IngredientCategoryRequest;
import com.request.IngredientItemRequest;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception{

        IngredientCategory item=ingredientsService.createIngredientCategory(req.getName(), req.getResturantId());
        return new ResponseEntity<>(item,HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientItemRequest req) throws Exception{

        IngredientsItem item=ingredientsService.createIngredientsItem(req.getResturantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception{

        IngredientsItem item=ingredientsService.updateStock(id);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @GetMapping("/resturant/{id}")
    public ResponseEntity<List<IngredientsItem>> getResturantIngredients(@PathVariable Long id) throws Exception{

        List<IngredientsItem> items=ingredientsService.findResturantsIngredients(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/resturant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getResturantIngredientCategory(@PathVariable Long id) throws Exception{

        List<IngredientCategory> items=ingredientsService.findIngredientCategoryByResturantId(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}
