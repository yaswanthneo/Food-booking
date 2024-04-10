package com.example.FoodDeivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeivery.model.DeliveryPerson;


@Repository

public interface DeliveryPersonRepository extends  CrudRepository<DeliveryPerson, Long> {
    
}