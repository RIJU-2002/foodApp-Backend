package com.foodapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.dto.ResturantDto;
import com.foodapp.model.Address;
import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.foodapp.repository.AddressRespository;
import com.foodapp.repository.ResturantRepository;
import com.foodapp.repository.UserRepository;
import com.request.CreateResturantRequest;

@Service
public class ResturantServiceImp implements ResturantService {

    @Autowired
    private ResturantRepository resturantRepository;

    @Autowired
    private AddressRespository addressRespository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Resturant createResturant(CreateResturantRequest req, User user) {
        
        Address address=addressRespository.save(req.getAddress());

        Resturant resturant=new Resturant();
        resturant.setAddress(address);
        resturant.setContactInformation(req.getContactInformation());
        resturant.setCuisineType(req.getCuisineType());
        resturant.setDescription(req.getDescription());
        resturant.setImages(req.getImages());
        resturant.setName(req.getName());
        resturant.setOpeningHours(req.getOpeningHours());
        resturant.setRegistrationDate(LocalDateTime.now());
        resturant.setOwner(user);

        return resturantRepository.save(resturant);
        
    }

    @Override
    public Resturant updateResturant(Long resturantId, CreateResturantRequest updatedResturant) throws Exception {
        Resturant resturant=findResturantById(resturantId);

        if (resturant.getCuisineType()!=null){
            resturant.setCuisineType(updatedResturant.getCuisineType());
        }
        if(resturant.getDescription()!=null){
            resturant.setDescription(updatedResturant.getDescription());
        }
        if(resturant.getName()!=null){
            resturant.setName(updatedResturant.getName());
        }

        return resturantRepository.save(resturant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
       Resturant resturant=findResturantById(resturantId);

        resturantRepository.delete(resturant);
    }

    @Override
    public List<Resturant> getAllResturant() {
        return resturantRepository.findAll();
    }

    @Override
    public List<Resturant> searchResturant(String keyword) {
       return resturantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Resturant findResturantById(Long id) throws Exception {
       Optional<Resturant> opt=resturantRepository.findById(id);

       if(opt.isEmpty()){
        throw new Exception("Resturant not found with id"+id);
       }
       return opt.get();
    }

    @Override
    public Resturant getResturantByUserId(Long userId) throws Exception {
        Resturant resturant=resturantRepository.findByOwnerId(userId);
        if(resturant==null){
            throw new Exception("Resturant not found with owner id"+userId);
        }
        return resturant;
    }

    @Override
    public ResturantDto addToFavorites(Long resturantId, User user) throws Exception {
        
        Resturant resturant=findResturantById(resturantId);

        ResturantDto dto=new ResturantDto();
        dto.setDescription(resturant.getDescription());
        dto.setImages(resturant.getImages());
        dto.setTitle(resturant.getName());
        dto.setId(resturantId);

        // if(user.getFavourites().contains(dto)){
        //     user.getFavourites().remove(dto);
        // }
        // else user.getFavourites().add(dto);

        boolean isFavorited=false;
        List<ResturantDto> favorites=user.getFavourites();
        for(ResturantDto favorite :favorites){
            if(favorite.getId().equals(resturantId)){
                isFavorited=true;
                break;
            }
        }

        if(isFavorited){
            favorites.removeIf(favorite -> favorite.getId().equals(resturantId));
        }
        else{
            favorites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Resturant updateResturantStatus(Long id) throws Exception {
        Resturant resturant=findResturantById(id);
        resturant.setOpen(!resturant.isOpen());
        return resturantRepository.save(resturant);
    }

}
