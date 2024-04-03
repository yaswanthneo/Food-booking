package com.foodDeliveryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.foodDeliveryApp.model.Orders;
@Repository

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    
}
