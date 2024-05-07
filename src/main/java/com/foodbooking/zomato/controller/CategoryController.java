package com.foodbooking.zomato.controller;

import java.util.List;

import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.model.Category;
import com.foodbooking.zomato.service.CategoryService;
import com.foodbooking.zomato.service.CategoryServiceImplementation;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {
	
	@Autowired
	public CategoryServiceImplementation categoryService;

	@Autowired
	public UserService userService;
	
	// POST endpoint for creating a category (accessible to admins)
	@PostMapping("/createcategory/{id}")
	public ResponseEntity<Category> createdCategory(
			@PathVariable Long id,
			@RequestBody Category category) throws RestaurantException, UserException {
				// Calls the userService to find the user profile by JWT token
		// User user=userService.findUserProfileByJwt(jwt);
		
				
		// Calls the categoryService to create a category with the provided name and user ID
		Category createdCategory=categoryService.createCategory(category.getName(), id);

		// Returns a ResponseEntity with the created category and HTTP status OK.
		return new ResponseEntity<Category>(createdCategory,HttpStatus.OK);
	}
	
	// GET endpoint for fetching categories of a restaurant
	@GetMapping("/getcategory/{id}")
	public ResponseEntity<List<Category>> getRestaurantsCategory(
			@PathVariable Long id) throws RestaurantException, UserException {

				// Calls the userService to find the user profile by JWT token
		// User user=userService.findUserProfileByJwt(jwt);

		// Calls the categoryService to find categories by restaurant ID
		
		List<Category> categories=categoryService.findCategoryByRestaurantId(id);

		// Returns a ResponseEntity with the list of categories and HTTP status OK (200)
		
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}

}
