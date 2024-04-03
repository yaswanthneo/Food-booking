package com.foodDeliveryApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDeliveryApp.model.Customer;
import com.foodDeliveryApp.model.Orders;
import com.foodDeliveryApp.repository.CustomerRepository;
import com.foodDeliveryApp.repository.OrdersRepository;

@Service


public class OrdersService {
    @Autowired
    private OrdersRepository orderRepository;
    //Service constructor
    public OrdersService(OrdersRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //Service used to place an order
    @SuppressWarnings("null")
	public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }
    //Service used to find a order based on id
    public Orders getOrderById(final Long aLong){
        Optional<Orders> order=orderRepository.findById(aLong);
        return order.orElse(null);
    }
    //Service used to find all the orders placed in the application

    public List<Orders> getAllOrder(){
        return (List<Orders>) orderRepository.findAll();
    }
    //Service to update details of the customer
    public Orders updateOrder(Orders order){
        Optional<Orders> optionalOrder=orderRepository.findById(order.getCustomerId());
        if(optionalOrder.isPresent()){
            return orderRepository.save(order);
        }else{
            return null;
        }
    }

    //Service used to delete order details based on r id
    
    // public void deleteById(final Long aId){
    //     Optional<Orders> optionalOrder=orderRepository.findById(aId);
    //     if(optionalOrder.isPresent()){
    //         orderRepository.deleteById(aId);
    //     }
    // }

}
