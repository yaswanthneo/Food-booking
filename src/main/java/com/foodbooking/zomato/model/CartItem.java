package com.foodbooking.zomato.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Define the CartItem entity class to represent an item in a user's shopping cart.
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore   // Ignore serialization of the cart field to prevent circular dependencies
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Food food;
    
    private int quantity;
    
    private Long totalPrice;
    
   
}

