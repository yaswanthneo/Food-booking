package com.foodbooking.zomato.Exception;


// Define a custom exception class for handling cart-related exceptions.
public class CartException extends Exception {


	// Constructor that takes a custom error message as a parameter
	public CartException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
	}

}
