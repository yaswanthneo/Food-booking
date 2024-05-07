package com.foodbooking.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbooking.zomato.Exception.FoodException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateFoodRequest;
import com.foodbooking.zomato.service.FoodService;
import com.foodbooking.zomato.service.UserService;
import java.util.*;


// Custom Comparator implementation for sorting food items by price in ascending order
class FoodPriceComparator implements Comparator<Food> {
    @Override
    public int compare(Food food1, Food food2) {
        // Compare prices of food items
        return Long.compare(food1.getPrice(), food2.getPrice());
    }
}


// Custom Comparator implementation for sorting food items by price in descending order
class FoodPriceDescendingComparator implements Comparator<Food> {
    @Override
    public int compare(Food food1, Food food2) {
        // Compare prices of food items in reverse order for descending sorting
        return Double.compare(food2.getPrice(), food1.getPrice());
    }
}


// RestController for handling food menu items
@RestController
@RequestMapping("/api/user/menuitem")
public class MenuItemController {
	@Autowired
	private FoodService menuItemService;
	
	@Autowired
	private UserService userService;

	// GET endpoint for searching food by name
	@GetMapping("/searchbyname")
	public ResponseEntity<List<Food>> searchFood(
			@RequestParam String name)  {

		// Calls the menuItemService to search for food items by name
		List<Food> menuItem = menuItemService.searchFood(name);
		return ResponseEntity.ok(menuItem);
	}

	// GET endpoint for retrieving menu items by restaurant ID and filters
	@GetMapping("/getmenu/{restaurantId}")
	public ResponseEntity<List<Food>> getMenuItemByRestaurantId(@PathVariable Long restaurantId) throws FoodException {

				// Calls the menuItemService to get menu items based on restaurant ID and filters
		List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);
		return ResponseEntity.ok(menuItems);
	}

	// GET endpoint for retrieving menu items by restaurant ID and sorting by price in ascending order
	@GetMapping("/getmenu/ascendingprice/{restaurantId}")
	public ResponseEntity<List<Food>> getMenuItemByPriceAsc(@PathVariable Long restaurantId) throws FoodException {

				// Calls the menuItemService to get menu items based on restaurant ID and filters
		List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);

		// Sort the list using the custom comparator for food price in ascending order
		Collections.sort(menuItems, new FoodPriceComparator());
		return ResponseEntity.ok(menuItems);
	}

	// GET endpoint for retrieving menu items by restaurant ID and sorting by price in descending order
	@GetMapping("/getmenu/descendingprice/{restaurantId}")
	public ResponseEntity<List<Food>> getMenuItemByPriceDesc(@PathVariable Long restaurantId) throws FoodException {

				// Calls the menuItemService to get menu items based on restaurant ID and filters
		List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);
		
    	// Sort the list using the custom comparator for food price in descending order
    	Collections.sort(menuItems, new FoodPriceDescendingComparator());
		return ResponseEntity.ok(menuItems);
	}

		// GET endpoint for retrieving menu items by restaurant ID and filters
		@GetMapping("/getvegitems/{restaurantId}")
		public ResponseEntity<List<Food>> getMenuItemVegetarian(@PathVariable Long restaurantId) throws FoodException {
	
					// Calls the menuItemService to get menu items based on restaurant ID and filters
			List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);
			List<Food> vegFood=menuItemService.filterByVegetarian(menuItems);
			return ResponseEntity.ok(vegFood);
		}
		
		@GetMapping("/getnonvegitems/{restaurantId}")
		public ResponseEntity<List<Food>> getMenuItemNonVegetarian(@PathVariable Long restaurantId) throws FoodException {
	
					// Calls the menuItemService to get menu items based on restaurant ID and filters
			List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);
			List<Food> nonVegFood=menuItemService.filterByNonveg(menuItems);
			return ResponseEntity.ok(nonVegFood);
		}

		@GetMapping("/getitemsbycategory/{restaurantId}")
		public ResponseEntity<List<Food>> getMenuItemByCategory(@PathVariable Long restaurantId,@RequestParam String category) throws FoodException {
	
					// Calls the menuItemService to get menu items based on restaurant ID and filters
			List<Food> menuItems= menuItemService.getRestaurantsFood(restaurantId);
			List<Food> menuItemsCategory=menuItemService.filterByFoodCategory(menuItems,category);
			return ResponseEntity.ok(menuItemsCategory);
		}



}
