package com.foodDeliveryApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodDeliveryApp.model.Customer;;

@Repository

public interface CustomerRepository extends  CrudRepository<Customer, Long> {
    
}

