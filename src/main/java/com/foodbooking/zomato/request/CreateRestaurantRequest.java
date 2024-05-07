package com.foodbooking.zomato.request;

import java.time.LocalDateTime;
import java.util.List;

import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.ContactInformation;
import com.foodbooking.zomato.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {

	private Long id;
	private User owner;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
}
