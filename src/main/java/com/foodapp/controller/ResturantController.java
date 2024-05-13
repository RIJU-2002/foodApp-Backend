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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.dto.ResturantDto;
import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.foodapp.service.ResturantService;
import com.foodapp.service.UserService;
import com.request.CreateResturantRequest;

@RestController
@RequestMapping("/api/resturants")
public class ResturantController {

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Resturant>> searchResturant(@RequestHeader("Authorization") String jwt,
                                                        @RequestParam String keyword) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        List<Resturant> resturant=resturantService.searchResturant(keyword);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Resturant>> getAllResturant(@RequestHeader("Authorization") String jwt
                                                        ) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        List<Resturant> resturant=resturantService.getAllResturant();
                                    
        return new ResponseEntity<>(resturant,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resturant> findResturantById(@RequestHeader("Authorization") String jwt,
                                                        @PathVariable Long id) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        Resturant resturant=resturantService.findResturantById(id);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<ResturantDto> addToFavorites(@RequestHeader("Authorization") String jwt,
                                                        @PathVariable Long id) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        ResturantDto resturant=resturantService.addToFavorites(id, user);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.OK);
    }

}
