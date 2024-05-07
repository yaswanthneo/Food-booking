package com.foodbooking.zomato.controller;

import java.util.List;

import com.foodbooking.zomato.model.Category;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.service.CategoryService;
import com.foodbooking.zomato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.foodbooking.zomato.Exception.FoodException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateFoodRequest;
import com.foodbooking.zomato.service.FoodService;
import com.foodbooking.zomato.service.UserService;

@RestController
@RequestMapping("/api/admin/menu")
public class AdminMenuItemController {
	
	@Autowired
	private FoodService menuItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CategoryService categoryService;

	// API endpoint for creating a new menu item
	// @PostMapping("/createitem")
	// public ResponseEntity<Food> createItem(
	// 		@RequestBody CreateFoodRequest item, 
	// 		@RequestHeader("Authorization") String jwt)
	// 		throws FoodException, UserException, RestaurantException {
	// 	System.out.println("req-controller ----"+item);
	// 	User user = userService.findUserProfileByJwt(jwt);
	// 	Category saved=categoryService.createCategory(item.getCategory().getName(),item.getRestaurantId());
	// 	Restaurant restaurant=restaurantService.findRestaurantById(item.getRestaurantId());
	// 		Food menuItem = menuItemService.createFood(item,saved,restaurant);
	// 		return ResponseEntity.ok(menuItem);

	// }


	// API endpoint for deleting a menu item by ID
	@DeleteMapping("/deleteitem/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id)
			throws UserException, FoodException {
		// User user = userService.findUserProfileByJwt(jwt);
		
			menuItemService.deleteFood(id);
			return ResponseEntity.ok("Menu item deleted");
		
	
	}

	
    // API endpoint for searching menu items by name

	@GetMapping("/searchitem")
	public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name)  {
		List<Food> menuItem = menuItemService.searchFood(name);
		return ResponseEntity.ok(menuItem);
	}
	
	// API endpoint for updating availability status of a menu item by ID
	@PutMapping("/updatestatus/{id}")
	public ResponseEntity<Food> updateAvilibilityStatus(
			@PathVariable Long id) throws FoodException {
		Food menuItems= menuItemService.updateAvailibilityStatus(id);
		return ResponseEntity.ok(menuItems);
	}

	@PutMapping("/updateitemprice/{id}")
	public ResponseEntity<Food> updateMenuItemPrice(
			@PathVariable Long id, @RequestParam Long price) throws FoodException {
		Food menuItems= menuItemService.updateMenuItemPrice(id,price);
		return ResponseEntity.ok(menuItems);
	}


	
	@SuppressWarnings("null")
	@PostMapping(value = "uploadmenu/{restaurantId}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadMenuItemBulk(@RequestParam("file") MultipartFile file, @PathVariable long restaurantId) {
		System.out.println(file.getContentType());
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "File is empty, please check and upload again");
        }
    
        // if (!file.getContentType().equals("text/csv")) {
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a CSV file");
        // }

        try {
            List<Food> foods = menuItemService.addMenuItemBulk(file, restaurantId);
            return ResponseEntity.status(HttpStatus.CREATED).body(foods);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	

}
