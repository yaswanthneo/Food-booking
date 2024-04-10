package com.example.FoodDeivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeivery.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
    // This interface extends CrudRepository to handle CRUD operations for the Restaurant entity.
    // It provides methods like save, findById, findAll, deleteById, etc., for managing Restaurant objects in the database.
}
