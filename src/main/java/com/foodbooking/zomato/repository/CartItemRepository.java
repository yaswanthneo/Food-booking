package com.foodbooking.zomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbooking.zomato.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


//    CartItem findByFoodIsContaining

}
