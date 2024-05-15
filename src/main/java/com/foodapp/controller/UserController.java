package com.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.model.Address;
import com.foodapp.model.User;
import com.foodapp.service.UserService;
import com.request.AddressRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/add/address")
    public ResponseEntity<List<Address>> addAddress(@RequestBody List<Address> req,
        @RequestHeader("Authorization") String jwt) throws Exception{
            User user=userService.findUserByJwtToken(jwt);
            user.setAddresses(req);

            List<Address> res=user.getAddresses();
            return new ResponseEntity<>(res,HttpStatus.OK);

    }
}
