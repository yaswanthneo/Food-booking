package com.foodbooking.zomato.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * ApiResponse class represents the response structure for API operations.
 * It includes a message and a status indicating the result of the operation.
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
	
	private String message;   // Represents a message describing the result of the operation
	private boolean status;   // Indicates the status of the operation (true for success, false for failure)

}
