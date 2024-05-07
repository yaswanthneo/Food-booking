package com.foodbooking.zomato.Exception;

// Define a custom exception class for handling restaurant-related exceptions.
public class RestaurantException extends Exception {

	    // Serial version UID to ensure version compatibility

	private static final long serialVersionUID = 1L;

	// Constructor that takes a custom error message as a parameter
	public RestaurantException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
		
	}
	

}
