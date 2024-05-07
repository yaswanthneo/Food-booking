package com.foodbooking.zomato.Exception;

// Define a custom exception class for handling cart item-related exceptions.
public class CartItemException extends Exception {
	
	// Constructor that takes a custom error message as a parameter
	public CartItemException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
	}

}
