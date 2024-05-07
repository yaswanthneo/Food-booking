package com.foodbooking.zomato.response;


import com.foodbooking.zomato.domain.USER_ROLE;
import lombok.Data;

/**
 * AuthResponse class represents the response structure for authentication operations.
 * It includes a message, a JWT token, and the user role.
 */

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	private USER_ROLE role;
	


}
