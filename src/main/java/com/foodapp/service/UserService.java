package com.foodapp.service;



import java.util.List;

import com.foodapp.model.Address;
import com.foodapp.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public List<Address> addAddressToProfile(List<Address> address,User user) throws Exception;
    
}
