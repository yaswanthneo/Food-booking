package com.foodbooking.zomato.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodbooking.zomato.Exception.FoodException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.model.Category;
// import com.foodbooking.zomato.model.IngredientsItem;
import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.Restaurant;
// import com.foodbooking.zomato.repository.IngredientsCategoryRepository;
import com.foodbooking.zomato.repository.foodRepository;
import com.foodbooking.zomato.repository.CategoryRepository;
import com.foodbooking.zomato.repository.RestaurantRepository;
import com.foodbooking.zomato.request.CreateFoodRequest;


@Service
public class FoodServiceImplementation implements FoodService {
	@Autowired
	private foodRepository foodRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Food createFood(CreateFoodRequest  req,
						   Category category,
						   Restaurant restaurant)
			throws FoodException,
	RestaurantException {

			Food food=new Food();
			food.setFoodCategory(category);
			food.setDescription(req.getDescription());
			food.setImages(req.getImages());
			food.setName(req.getName());
			food.setPrice((long) req.getPrice());		
			food.setVegetarian(req.isVegetarian());
		food.setRestaurant(restaurant);
			food = foodRepository.save(food);

			restaurant.getFoods().add(food);
			return food;
		
	}

	@Override
	public void deleteFood(Long foodId) throws FoodException {
		Food food=findFoodById(foodId);
		food.setRestaurant(null);;
//		foodRepository.save(food);
		foodRepository.delete(food);

	}


	@Override
	public List<Food> getRestaurantsFood(Long restaurantId) throws FoodException {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		
		// boolean isVegetarian, 
		// 	boolean isNonveg,
		// 	boolean isSeasonal,
		// 	String foodCategory
	    // if (isVegetarian) {
	    //     foods = filterByVegetarian(foods, isVegetarian);
	    // }
	    // if (isNonveg) {
	    //     foods = filterByNonveg(foods, isNonveg);
	    // }

	    // if (isSeasonal) {
	    //     foods = filterBySeasonal(foods, isSeasonal);
	    // }
	    // if(foodCategory!=null && !foodCategory.equals("")) {
	    // 	foods = filterByFoodCategory(foods, foodCategory);
	    // }
		
		return foods;
		
	}
	
	public List<Food> filterByVegetarian(List<Food> foods) throws FoodException {
	    return foods.stream()
	            .filter(food -> food.isVegetarian() == true)
	            .collect(Collectors.toList());
	}
	public List<Food> filterByNonveg(List<Food> foods) throws FoodException {
	    return foods.stream()
	            .filter(food -> food.isVegetarian() == false)
	            .collect(Collectors.toList());
	}
	
	public List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) throws FoodException {
	    
		List<Food> foodByCategory=new ArrayList<>();
		for(Food food : foods){
			if(food.getFoodCategory().getName().toLowerCase().equals(foodCategory.toLowerCase())){
				foodByCategory.add(food);
			}
		}
		return foodByCategory;
	}
	
	@Override
	public List<Food> searchFood(String keyword) {
		List<Food> items = new ArrayList<>();
		List<Food> allItems = foodRepository.findAll();
		keyword = keyword.toLowerCase(); // Convert keyword to lowercase for case-insensitive comparison
		System.out.println("keyword -- " + keyword);
		for (Food item : allItems) {
			// Check if the lowercase item name contains the lowercase keyword
			if (item.getName().toLowerCase().contains(keyword)) {
				items.add(item);
			}
		}
		return items;
	}
	@Override
	public Food updateAvailibilityStatus(Long id) throws FoodException {
		Food food = findFoodById(id);
		
		food.setAvailable(!food.isAvailable());
		foodRepository.save(food);
		return food;
	}

	@Override
	public Food findFoodById(Long foodId) throws FoodException {
		Optional<Food> food = foodRepository.findById(foodId);
		if (food.isPresent()) {
			return food.get();
		}
		throw new FoodException("food with id" + foodId + "not found");
	}


	@Override
    public List<Food> addMenuItemBulk(MultipartFile file, long restaurantId) throws Exception {
        List<Food> foods = new ArrayList<>();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new Exception("Restaurant not found"));

        String[] csv = new String(file.getBytes()).split("\\r?\\n");
        for (int ind = 1; ind < csv.length; ind++) {
            String[] values = csv[ind].split("\t");
            Food food = new Food();
            food.setName(values[0]);
            food.setDescription(values[1]);
            food.setPrice(Long.parseLong(values[2]));

            Category category = new Category();
            category.setName(values[3]);


            category.setRestaurant(restaurant);
            food.setFoodCategory(categoryRepository.save(category));
            
            food.setImages(Arrays.asList(values[4]));
            if(!(values[5].toLowerCase().trim().equals("true")) && !(values[5].toLowerCase().trim().equals("false"))) {
                throw new Exception("IsVegetarian should either be true or false");
            }
            food.setVegetarian(Boolean.parseBoolean(values[5]));
            food.setRestaurant(restaurant);

            food = foodRepository.save(food);
            foods.add(food);
        }

        return foods;
    }

	public Food updateMenuItemPrice( Long id, Long price) throws FoodException{
		
		Optional<Food> foodOptional=foodRepository.findById(id);
		Food newFood=foodOptional.get();
		newFood.setPrice(price);
		Food savedFood=foodRepository.save(newFood);
		return savedFood;
		
	}

}
