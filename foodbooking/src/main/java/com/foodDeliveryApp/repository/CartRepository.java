package com.foodDeliveryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodDeliveryApp.model.Cart;

@Repository

public interface CartRepository extends JpaRepository<Cart,Long>{
    
}
