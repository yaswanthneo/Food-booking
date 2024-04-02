package com.foodDeliveryApp.controller;

import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodDeliveryApp.model.Customer;
import com.foodDeliveryApp.service.CustomerService;

@RestController


@RequestMapping("/api/customers")

public class CustomerController {
    private final CustomerService customersService;
    //Controller constructor
    public CustomerController(CustomerService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    //Register the customer details 
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customersService.save(customer);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    //Get all the customers registered in the application
    public Iterable<Customer> getAllCustomers() {
        return customersService.findAll();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Get a customer registered in the application based on his/her id
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customersService.findById(id);
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Update the customer details
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return customersService.save(customer);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Delete a customer details based on his/her id
    public void deleteCustomer(@PathVariable("id") Long id) {
        customersService.deleteById(id);
    }



}

