package com.foodbooking.zomato.Exception;

// Define a custom exception class for handling user-related exceptions.
public class UserException extends Exception {
	
	// Constructor that takes a custom error message as a parameter
	
	public UserException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
	}

}
