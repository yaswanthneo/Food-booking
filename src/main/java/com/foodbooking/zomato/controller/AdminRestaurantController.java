package com.foodbooking.zomato.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.foodbooking.zomato.Exception.AddressException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateRestaurantRequest;
import com.foodbooking.zomato.response.ApiResponse;
import com.foodbooking.zomato.service.RestaurantService;
import com.foodbooking.zomato.service.UserService;
import com.foodbooking.zomato.repository.AddressRepository;
import com.foodbooking.zomato.repository.UserRepository;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;


	// // API endpoint for creating a new restaurant
	// @PostMapping("/createrestaurant")
	// public ResponseEntity<Restaurant> createRestaurant(
	// 		@RequestBody CreateRestaurantRequest req,
	// 		@RequestHeader("Authorization") String jwt) throws UserException {

	// 		User user = userService.findUserProfileByJwt(jwt);
		
	// 		System.out.println("----TRUE___-----"+jwt);
	// 		Restaurant restaurant = restaurantService.createRestaurant(req);
	// 		return ResponseEntity.ok(restaurant);
	// }

	// GET endpoint for retrieving restaurant profile
	@GetMapping("/profile/{id}")
	public ResponseEntity<User> getUserProfileHandler(@PathVariable Long id) throws UserException {
		// This method handles GET requests to "/api/users/profile" for fetching user profiles.
        // It requires an Authorization header with a JWT token.

        // Calls the userService to find the user profile based on the JWT token.

		Optional<User> optionalUser= userRepository.findById(id);
		User user=optionalUser.get();
		// Sets the password field of the user object to null for security reasons before returning it.
		user.setPassword(null);

		// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

// API endpoint for updating a restaurant by ID
	@PutMapping("updaterestaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody CreateRestaurantRequest req
			) throws RestaurantException, UserException {
		    Restaurant currentRestaurant=restaurantService.findRestaurantById(id);
			Optional<User> optionalUser=userRepository.findById(currentRestaurant.getOwner().getId());
			User user=optionalUser.get();
			Restaurant restaurant = restaurantService.updateRestaurant(id, req,user);
			return ResponseEntity.ok(restaurant);
		
	}

	// API endpoint for deleting a restaurant by ID

	// @DeleteMapping("/{id}")
	// public ResponseEntity<ApiResponse> deleteRestaurantById(@PathVariable("id") Long restaurantId,
	// 		@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
	// 	User user = userService.findUserProfileByJwt(jwt);
		
	// 		restaurantService.deleteRestaurant(restaurantId);
			
	// 		ApiResponse res=new ApiResponse("Restaurant Deleted with id Successfully",true);
	// 		return ResponseEntity.ok(res);
	// }

	// API endpoint for updating the status of a restaurant by ID
	
	@PutMapping("/updaterestaurantstatus/{id}")
	public ResponseEntity<Restaurant> updateRestaurantStatus(
			@PathVariable Long id) throws RestaurantException, UserException {
		
			Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
			return ResponseEntity.ok(restaurant);

	}




	// PUT endpoint for adding restaurant address
	// @PutMapping("/addaddress")
	// public ResponseEntity<User> addUserAddress(@RequestBody Address address,@RequestHeader("Authorization") String jwt) throws UserException,AddressException {
		

	// 	User user = userService.findUserProfileByJwt(jwt);
	// 	Address createAddress= userService.createAddress(address, user);
	// 	// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
	// 	return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	// }

	// PUT endpoint for deleting restaurant address by ID
	// @PutMapping("/deleteaddress/{id}")
	// public ResponseEntity<User> deleteUserAddress(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws UserException {
		
	// 	// Find the user profile based on the JWT token
	// 	User user = userService.findUserProfileByJwt(jwt);

	// 	// Set the password field of the user object to null for security reasons
		
	// 	// Find the address to delete by ID
	// 	Optional<Address> optionalDeleteAddress=addressRepository.findById(id);
	// 	Address deleteAddress=optionalDeleteAddress.get();

	// 	 // Remove the address from the user's addresses
	// 	user.getAddresses().remove(deleteAddress);
	// 	userRepository.save(user);

		
	// 	// Delete the address from the database
	// 	addressRepository.delete(deleteAddress);
		
	// 	// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
	// 	return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	// }
	
	

}
