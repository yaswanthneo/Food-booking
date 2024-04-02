package com.foodDeliveryApp.controller;

import java.util.List;
import java.util.Optional;

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
import com.foodDeliveryApp.model.Customer;
import com.foodDeliveryApp.service.CustomerService;

@RestController


@RequestMapping("/api/customers")

public class CustomerController {
    @Autowired
    private final CustomerService customersService;
    //Controller constructor
    public CustomerController(CustomerService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    //Register the customer details 
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer=customersService.addCustomer(customer);
        return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    //Get all the customers registered in the application
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customersService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Get a customer registered in the application based on his/her id
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer=customersService.getCustomerById(id);
        if(customer!=null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Update the customer details
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        Customer updatedCustomer=customersService.updateCustomer(customer);
        if(updatedCustomer!=null){
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Delete a customer details based on his/her id
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customersService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

