package com.example.FoodDeivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.FoodDeivery.model.Restaurant;
import com.example.FoodDeivery.service.RestaurantService;


@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    //Endpoint to get a new restaurant, accessible to users with "ROLE_RESTAURANT"
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurant), HttpStatus.CREATED);
    }

    // Endpoint to get all restaurants, accessible to users with "ROLE_ADMIN" and "ROLE_CUSTOMER"
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    //Endpoint to get a restaurant by ID, accessible to users with "ROLE_ADMIN" and "ROLE_CUSTOMER"
    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
 
    


    // Endpoint to update a restaurant, accessible only to users with "ROLE_RESTAURANT"
    @PutMapping("/{id}")
    @Secured("ROLE_RESTAURANT")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        restaurant.setRestaurantId(id);
        Restaurant updatedRestaurant=restaurantService.updateRestaurant(restaurant);
        if(updatedRestaurant!=null){
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    // Endpoint to delete a restaurant, accessible to users with "ROLE_ADMIN" and "ROLE_RESTAURANT"    
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_RESTAURANT"})
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}