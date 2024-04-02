package com.foodDeliveryApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodDeliveryApp.model.DeliveryPerson;;

@Repository

public interface DeliveryPersonRepository extends  CrudRepository<DeliveryPerson, Long> {
    
}

