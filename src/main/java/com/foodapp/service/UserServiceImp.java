package com.foodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.config.JwtProvider;
import com.foodapp.model.Address;
import com.foodapp.model.User;
import com.foodapp.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        // TODO Auto-generated method stub
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        // TODO Auto-generated method stub
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found....");
        }
        return user;
    }

    @Override
    public List<Address> addAddressToProfile(List<Address> address,User user) throws Exception {
        user.setAddresses(address);
        userRepository.save(user);

        return user.getAddresses();

    }

}
