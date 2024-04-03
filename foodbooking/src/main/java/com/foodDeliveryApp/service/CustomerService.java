package com.foodDeliveryApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDeliveryApp.model.Customer;
import com.foodDeliveryApp.repository.CustomerRepository;

@Service

public final class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    //Service constructor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //Service used to register a customer or update a customer
    @SuppressWarnings("null")
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    //Service used to find a customer based on his/her id
    public Customer getCustomerById(final Long aLong){
        Optional<Customer> customer=customerRepository.findById(aLong);
        return customer.orElse(null);
    }
    //Service used to find all the customers registered in the application

    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
    //Service to update details of the customer
    public Customer updateCustomer(Customer customer){
        Optional<Customer> optionalCustomer=customerRepository.findById(customer.getCustomerId());
        if(optionalCustomer.isPresent()){
            return customerRepository.save(customer);
        }else{
            return null;
        }
    }

    //Service used to delete customer details based on his/her id
    
    public void deleteById(final Long aId){
        Optional<Customer> optionalCustomer=customerRepository.findById(aId);
        if(optionalCustomer.isPresent()){
            customerRepository.deleteById(aId);
        }
    }

}