package com.foodbooking.zomato.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.foodbooking.zomato.Exception.FoodException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.model.Category;
import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category,
						   Restaurant restaurant) throws FoodException, RestaurantException;

	void deleteFood(Long foodId) throws FoodException;
	
	public List<Food> getRestaurantsFood(Long restaurantId) throws FoodException;
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws FoodException;

	public Food updateAvailibilityStatus(Long foodId) throws FoodException;

	public List<Food> addMenuItemBulk(MultipartFile file, long restaurantId) throws Exception;

	public Food updateMenuItemPrice( Long id, Long price) throws FoodException; 

	public List<Food> filterByVegetarian(List<Food> foods)throws FoodException;

	public List<Food> filterByNonveg(List<Food> foods) throws FoodException;

	public List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) throws FoodException;
}
