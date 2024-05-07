package com.foodbooking.zomato.service;

import java.util.List;

import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.model.Category;

public interface CategoryService {
	
	public Category createCategory (String name,Long userId) throws RestaurantException;
	public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
	public Category findCategoryById(Long id) throws RestaurantException;

}
