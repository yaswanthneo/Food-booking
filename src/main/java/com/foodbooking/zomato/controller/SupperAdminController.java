package com.foodbooking.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.request.CreateRestaurantRequest;
import com.foodbooking.zomato.response.ApiResponse;
import com.foodbooking.zomato.service.RestaurantService;
import com.foodbooking.zomato.service.UserService;


// RestController for handling super admin operations
@RestController
@RequestMapping("/api/superadmin")
public class SupperAdminController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	// POST endpoint for creating a restaurant
	@PostMapping("/createrestaurants")
	public ResponseEntity<Restaurant> createRestaurant(
			@RequestBody CreateRestaurantRequest req
			) throws UserException {

			// User user = userService.findUserProfileByJwt(jwt);
		
			// System.out.println("----TRUE___-----"+jwt);

			// Create a new restaurant using the request and return the response
			Restaurant restaurant = restaurantService.createRestaurant(req);
			return ResponseEntity.ok(restaurant);
	}

	// GET endpoint for retrieving all customers

	@GetMapping("/viewallcustomers")
	public ResponseEntity<List<User>> getAllCustomers() {

		
		
		List<User> users =userService.findAllUsers();
		// Returns a ResponseEntity with the list of users and HTTP status ACCEPTED.
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);

	}
	

	// GET endpoint for finding a restaurant by user ID

	// @GetMapping("/viewrestaurant/{id}")
	// public ResponseEntity<Restaurant> findRestaurantByUserId(
	// 		@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
	// 	User user = userService.findUserProfileByJwt(jwt);
	// 	Restaurant restaurant = restaurantService.getRestaurantsByUserId(user.getId());
	// 	return ResponseEntity.ok(restaurant);

	// }


// GET endpoint for searching restaurants by keyword
    @GetMapping("/searchrestaurantbyname")
    public ResponseEntity<List<Restaurant>> findRestaurantByName(
            @RequestParam String keyword) {
 
                // Calls the restaurantService to search for restaurants by keyword
        
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
 
        // Returns a ResponseEntity with the list of restaurants and HTTP status OK
        return ResponseEntity.ok(restaurant);
    }
 
 
    // GET endpoint for retrieving all restaurants
    @GetMapping("/viewallrestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
 
        // Calls the restaurantService to retrieve all restaurants
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        
        // Returns a ResponseEntity with the list of restaurants and HTTP status OK
        return ResponseEntity.ok(restaurants);
    }
    
    // GET endpoint for finding a restaurant by ID
    @GetMapping("/viewrestaurant/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Long id) throws RestaurantException {
 
            Restaurant restaurant = restaurantService.findRestaurantById(id);
            return ResponseEntity.ok(restaurant);
 
    }

	// API endpoint for deleting a restaurant by ID
	@DeleteMapping("/deleterestaurant/{id}")
	public ResponseEntity<ApiResponse> deleteRestaurantById(@PathVariable("id") Long restaurantId
			) throws RestaurantException, UserException {
		// User user = userService.findUserProfileByJwt(jwt);
		
			restaurantService.deleteRestaurant(restaurantId);
			
			ApiResponse res=new ApiResponse("Restaurant Deleted with id Successfully",true);
			return ResponseEntity.ok(res);
	}
}
