package com.foodbooking.zomato.Exception;


// Define a custom exception class for handling order-related exceptions.
public class OrderException extends Exception {

	// Constructor that takes a custom error message as a parameter
	public OrderException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
		
	}
	

}
