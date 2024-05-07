package com.foodbooking.zomato.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

//--------------------------------------------------------------------------------------------------

// Explanation:

// The RestaurantDto class is a Data Transfer Object used to transfer restaurant-related information.
// It includes fields such as title, images, description, and id.
// The @Embeddable annotation indicates that this class can be embedded in other entities.
// The @Data annotation from Lombok generates boilerplate code like getters, setters, toString(), and others, reducing code verbosity.
// The @Column(length = 1000) annotation specifies the maximum length for the images column in the database to avoid data truncation.

//--------------------------------------------------------------------------------------------------



// Define a Data Transfer Object (DTO) for representing restaurant information.
// Annotate the class with @Embeddable to indicate that it can be embedded in other entities.

@Data  // Lombok annotation to generate getters, setters, toString, etc.
@Embeddable
public class RestaurantDto {
	
	// Define fields for restaurant information

	private String title;


	@Column(length = 1000)    // Specify the maximum length for the images column
	private List<String> images;    // List of image URLs for the restaurant

	private String description;
	private Long id;
	

}
