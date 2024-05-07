package com.foodbooking.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbooking.zomato.Exception.AddressException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.repository.AddressRepository;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.service.RestaurantService;
import com.foodbooking.zomato.service.UserService;
import java.util.List;
import java.util.Optional;


// RestController for handling user-related operations
@RestController
@RequestMapping("/api/user")
public class UserController {

	// Autowired userService for user-related operations
	private UserService userService;

	// Constructor for dependency injection of userService
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
    private RestaurantService restaurantService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;


	 // GET endpoint for retrieving user profile
	@GetMapping("/profile/{id}")
	public ResponseEntity<User> getUserProfileHandler(@PathVariable Long id) throws UserException {
		// This method handles GET requests to "/api/users/profile" for fetching user profiles.
        // It requires an Authorization header with a JWT token.

        // Calls the userService to find the user profile based on the JWT token.
		Optional<User> optionalUser=userRepository.findById(id);
		User user = optionalUser.get();
		// Sets the password field of the user object to null for security reasons before returning it.
		user.setPassword(null);

		// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
	
	// GET endpoint for searching restaurants by keyword
    @GetMapping("/searchbyname")
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

	// PUT endpoint for adding user address
	@PutMapping("/{id}/addaddress")
	public ResponseEntity<User> addUserAddress(@PathVariable Long id,@RequestBody Address address) throws UserException,AddressException {
		

		Optional<User> optionalUser=userRepository.findById(id);
		User user = optionalUser.get();
		Address createAddress= userService.createAddress(address, user);
		// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

		// PUT endpoint for updating user address
		@PutMapping("/{userId}/updateaddress/{id}")
		public ResponseEntity<User> updateUserAddress(@PathVariable Long userId,@PathVariable Long id,@RequestBody Address address) throws UserException,AddressException {
			
	
			Optional<User> optionalUser=userRepository.findById(userId);
			User user = optionalUser.get();
			Address updateAddress= userService.updateUserAddress(id,address, user);
			// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
			return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		}

	// PUT endpoint for deleting user address by ID
	@PutMapping("/{userId}/deleteaddress/{id}")
	public ResponseEntity<User> deleteUserAddress(@PathVariable Long userId,@PathVariable Long id) throws UserException {
		
		// Find the user profile based on the JWT token
		Optional<User> optionalUser=userRepository.findById(userId);
		User user = optionalUser.get();

		
		// Find the address to delete by ID
		Optional<Address> optionalDeleteAddress=addressRepository.findById(id);
		Address deleteAddress=optionalDeleteAddress.get();

		 // Remove the address from the user's addresses
		user.getAddresses().remove(deleteAddress);
		userRepository.save(user);

		
		// Delete the address from the database
		addressRepository.delete(deleteAddress);
		
		// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
}
