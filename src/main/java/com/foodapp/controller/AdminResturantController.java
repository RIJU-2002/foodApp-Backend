package com.foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.foodapp.response.MessageResponse;
import com.foodapp.service.ResturantService;
import com.foodapp.service.UserService;
import com.request.CreateResturantRequest;

@RestController
@RequestMapping("/api/admin/resturants")
public class AdminResturantController {

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Resturant> createResturant(@RequestBody CreateResturantRequest req,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        Resturant resturant=resturantService.createResturant(req, user);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resturant> updateResturant(@RequestBody CreateResturantRequest req,
                                                    @RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        Resturant resturant=resturantService.updateResturant(id, req);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteResturant(
                                                    @RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        resturantService.deleteResturant(id);
                                    
        MessageResponse res=new MessageResponse();
        res.setMessage("Resturant deleted successfully");
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Resturant> updateResturantStatus(
                                                    @RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        Resturant resturant=resturantService.updateResturantStatus(id);
                                    
        return new ResponseEntity<>(resturant,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Resturant> findResturantByUserId(
                                                    @RequestHeader("Authorization") String jwt
                                                    ) throws Exception{
                                                        
        User user=userService.findUserByJwtToken(jwt); 
        
        Resturant resturant=resturantService.getResturantByUserId(user.getId());
                                    
        return new ResponseEntity<>(resturant,HttpStatus.OK);
    }

}
