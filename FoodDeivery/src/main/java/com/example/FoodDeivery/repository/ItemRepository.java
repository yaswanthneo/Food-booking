package com.example.FoodDeivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeivery.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}