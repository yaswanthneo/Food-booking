package com.example.FoodDeivery.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.FoodDeivery.model.Customer;


@Repository

public interface CustomerRepository extends  JpaRepository<Customer, Long> {
    
}