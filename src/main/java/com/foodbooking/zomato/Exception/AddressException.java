package com.foodbooking.zomato.Exception;


//----------------------------

// Explanation:

// The AddressException class is a custom exception class used to handle exceptions related to addresses in the application.
// It extends the Java built-in Exception class, making it a checked exception.
// The constructor public AddressException(String message) takes a custom error message as a parameter.
// Inside the constructor, it calls the superclass constructor super(message) to set the error message for the exception.
// This class allows you to throw and catch specific exceptions related to address operations, providing better control and error handling in your application.

//-------------------------------




// Define a custom exception class for handling address-related exceptions.
public class AddressException extends Exception {

	// Constructor that takes a custom error message as a parameter
    public AddressException(String message) {

		// Call the superclass (Exception) constructor with the provided message
		super(message);
	}
}
