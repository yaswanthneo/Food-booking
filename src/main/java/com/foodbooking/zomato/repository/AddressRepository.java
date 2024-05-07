package com.foodbooking.zomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbooking.zomato.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
