package main.java.com.foodbooking.zomato.request;

import java.util.List;

import com.foodbooking.zomato.model.Food;

import lombok.Data;

@Data
public class AddCartItemRequest {
	
	private Long menuItemId;
	private int quantity;
	// private List<String> ingredients;

}
