package com.example.FoodDeivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId; // Unique identifier for the restaurant

    private String restaurantName; // Name of the restaurant
    private String restaurantAddress; // Address of the restaurant
    private String restaurantPhone; // Phone number of the restaurant
 
}
