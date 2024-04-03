package com.foodDeliveryApp.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import com.foodDeliveryApp.model.Orders;

import com.foodDeliveryApp.service.OrdersService;

@RestController


@RequestMapping("/api/orders")

public class OrdersController {
    @Autowired
    private final OrdersService orderService;
    //Controller constructor
    public OrdersController(OrdersService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    //Placing order
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order) {
        Orders createdOrder=orderService.addOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    @Secured("ROLE_RESTAURANT")
    //View all the orders placed by the customer
    public ResponseEntity<List<Orders>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_CUSTOMER","ROLE_RESTAURANT","ROLE_DELIVERYPERSON"})
    //View order details based on id
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order=orderService.getOrderById(id);
        if(order!=null){
            return new ResponseEntity<>(order, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @PutMapping("/{id}")
    @Secured({"ROLE_RESTAURANT","ROLE_DELIVERYPERSON"})
    //Update the order status and delivery status
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order) {
        order.setCustomerId(id);
        Orders updatedOrder=orderService.updateOrder(order);
        if(updatedOrder!=null){
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @DeleteMapping("/{id}")
    // @Secured("ROLE_CUSTOMER")
    // //Delete a customer details based on his/her id
    // public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
    //     customersService.deleteById(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
