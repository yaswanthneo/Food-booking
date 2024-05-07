package com.foodbooking.zomato.Exception;

// Define a custom exception class for handling food-related exceptions.
public class FoodException extends Exception {

	 // Constructor that takes a custom error message as a parameter
	public FoodException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);

	}

}
