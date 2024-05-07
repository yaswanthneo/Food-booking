package com.foodbooking.zomato.request;

import java.util.List;

import com.foodbooking.zomato.model.Food;

import lombok.Data;

//--------------
// In the AddCartItemRequest class:

// menuItemId: Represents the ID of the menu item (food) that the user wants to add to the cart.
// quantity: Indicates the quantity of the menu item to be added to the cart.
// The @Data annotation from Lombok is used to automatically generate constructors, getters, setters, equals(), hashCode(), and toString() methods for the class, reducing boilerplate code. This makes the class concise and easier to maintain.

//------------------



@Data
public class AddCartItemRequest {
	
	private Long menuItemId;
	private int quantity;

}
