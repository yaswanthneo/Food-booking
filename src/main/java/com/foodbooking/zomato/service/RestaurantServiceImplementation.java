package com.foodbooking.zomato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.controller.AuthController;
import com.foodbooking.zomato.dto.RestaurantDto;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.repository.AddressRepository;
import com.foodbooking.zomato.repository.RestaurantRepository;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.request.CreateRestaurantRequest;
import com.foodbooking.zomato.response.AuthResponse;


/**
 * RestaurantServiceImplementation provides the implementation for RestaurantService.
 * It handles restaurant-related operations such as creating, updating, and deleting restaurants.
 */


@Service
public class RestaurantServiceImplementation implements RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthController authController;
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req) throws UserException {
		Address address=new Address();
		address.setCity(req.getAddress().getCity());
		address.setHouseNo(req.getAddress().getHouseNo());
		address.setFullName(req.getAddress().getFullName());
		address.setPostalCode(req.getAddress().getPostalCode());
		address.setState(req.getAddress().getState());
		address.setStreetAddress(req.getAddress().getStreetAddress());
		address.setMobileNo(req.getAddress().getMobileNo());
		address.setLandmark(req.getAddress().getLandmark());
		
		Address savedAddress = addressRepository.save(address);
		List<Address> addresses= new ArrayList<>();
		addresses.add(savedAddress);
		User user=new User();
		user.setFullName(req.getOwner().getFullName());
		user.setEmail(req.getOwner().getEmail());
		user.setPassword(req.getOwner().getPassword());
		user.setRole(req.getOwner().getRole());
		user.setAddresses(addresses);
		ResponseEntity<AuthResponse> createUser=authController.createUserHandler(user);
		User newUser=userService.findUserByEmail(req.getOwner().getEmail());
		// User savedUser=userRepository.save(user);

		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(savedAddress);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		// restaurant.setRegistrationDate(req.getRegistrationDate());
		restaurant.setOwner(newUser);
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);

		return savedRestaurant;
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedReq,User user)
			throws RestaurantException,UserException {
		Restaurant updateRestaurant = findRestaurantById(restaurantId);
		Address address=new Address();
		address.setId(updatedReq.getAddress().getId());
		address.setCity(updatedReq.getAddress().getCity());
		address.setHouseNo(updatedReq.getAddress().getHouseNo());
		address.setFullName(updatedReq.getAddress().getFullName());
		address.setPostalCode(updatedReq.getAddress().getPostalCode());
		address.setState(updatedReq.getAddress().getState());
		address.setStreetAddress(updatedReq.getAddress().getStreetAddress());
		address.setMobileNo(updatedReq.getAddress().getMobileNo());
		address.setLandmark(updatedReq.getAddress().getLandmark());
		Address savedAddress=new Address();
		if(!address.equals(updateRestaurant.getAddress())){
			savedAddress=addressRepository.save(address);
			updateRestaurant.setAddress(savedAddress);
		}
		List<Address> addresses= new ArrayList<>();
		addresses.add(savedAddress);
		User newUser=new User();
		newUser.setFullName(updatedReq.getOwner().getFullName());
		newUser.setEmail(updatedReq.getOwner().getEmail());
		newUser.setPassword(updatedReq.getOwner().getPassword());
		newUser.setRole(updatedReq.getOwner().getRole());
		newUser.setAddresses(addresses);
		newUser.setId(user.getId());
		//User newUser=userService.findUserByEmail(updatedReq.getOwner().getEmail());
		// User savedUser=userRepository.save(user);

		
		
		updateRestaurant.setContactInformation(updatedReq.getContactInformation());
		updateRestaurant.setCuisineType(updatedReq.getCuisineType());
		updateRestaurant.setDescription(updatedReq.getDescription());
		updateRestaurant.setImages(updatedReq.getImages());
		updateRestaurant.setName(updatedReq.getName());
		updateRestaurant.setOpeningHours(updatedReq.getOpeningHours());
		// restaurant.setRegistrationDate(req.getRegistrationDate());
		if(!user.equals(newUser)){

			updateRestaurant.setOwner(newUser);
			userRepository.save(newUser);
		}
		
	
		
		return restaurantRepository.save(updateRestaurant);
	}
	
	@Override
	public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if (restaurant.isPresent()) {
			return restaurant.get();
		} else {
			throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
		}
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws RestaurantException {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant != null) {
			restaurantRepository.delete(restaurant);
			return;
		}
		throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");

	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}


	@Override
	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
		Restaurant restaurants=restaurantRepository.findByOwnerId(userId);
		return restaurants;
	}



	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException {
		Restaurant restaurant=findRestaurantById(restaurantId);
		
		RestaurantDto dto=new RestaurantDto();
		dto.setTitle(restaurant.getName());
		dto.setImages(restaurant.getImages());
		dto.setId(restaurant.getId());
		dto.setDescription(restaurant.getDescription());


		//---------------------------------------------------------------------------
		// boolean isFavorited = false;
		// List<RestaurantDto> favorites = user.getFavorites();
		// for (RestaurantDto favorite : favorites) {
		// 	if (favorite.getId().equals(restaurantId)) {
		// 		isFavorited = true;
		// 		break;
		// 	}
		// }
		

		// if (isFavorited) {
		// 	favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		// } else {
		// 	favorites.add(dto);
		// }

		//-----------------------------------------------
		
		User updatedUser = userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

}
