package com.foodbooking.zomato.service;

import java.util.List;

import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.dto.RestaurantDto;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req) throws UserException;

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant,User user)
			throws RestaurantException,UserException;

	public void deleteRestaurant(Long restaurantId) throws RestaurantException;

	public List<Restaurant>getAllRestaurant();

	public List<Restaurant>searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws RestaurantException;

	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;
	
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException;

	public Restaurant updateRestaurantStatus(Long id)throws RestaurantException;
}
