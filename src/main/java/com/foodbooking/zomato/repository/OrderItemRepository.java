package com.foodbooking.zomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbooking.zomato.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
