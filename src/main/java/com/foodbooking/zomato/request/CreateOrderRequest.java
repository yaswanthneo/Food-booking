package com.foodbooking.zomato.request;

import com.foodbooking.zomato.model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long restaurantId;
	
	private Long addressId;
	
    
}
