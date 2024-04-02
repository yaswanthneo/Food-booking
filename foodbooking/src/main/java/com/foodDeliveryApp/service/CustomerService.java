package com.foodDeliveryApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDeliveryApp.model.Customer;
import com.foodDeliveryApp.repository.CustomerRepository;

@Service

public final class CustomerService {
    @Autowired
    private  final CustomerRepository repository;
    //Service constructor
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    //Service used to register a customer or update a customer
    public <S extends Customer> S save(final S entity) {
        return repository.save(entity);
    }
    //Service used to find a customer based on his/her id
    public Optional<Customer> findById(final Long aLong){
        return repository.findById(aLong);
    }
    //Service used to find all the customers registered in the application
    public Iterable<Customer> findAll(){
        return repository.findAll();
    }
    //Service used to delete customer details based on his/her id
    
    public void deleteById(final Long aId){
        repository.deleteById(aId);
    }

}