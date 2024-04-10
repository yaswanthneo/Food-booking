package com.example.FoodDeivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeivery.model.Restaurant;
import com.example.FoodDeivery.repository.RestaurantRepository;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    // Method to create a new restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
   
    // Method to get a restaurant by ID
    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> optionalItem = restaurantRepository.findById(id);
        return optionalItem.orElse(null);
    }

    // Method to get all restaurants
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    
    // Method to update an existing restaurant
    public Restaurant updateRestaurant(Restaurant restaurant){
        Optional<Restaurant> optionalRestaurant=restaurantRepository.findById(restaurant.getRestaurantId());
        if(optionalRestaurant.isPresent()){
            return restaurantRepository.save(restaurant);
        }else{
            return null;
        }
    }

    // Method to delete a restaurant by ID
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }
    
}





    
