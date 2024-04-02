package com.foodDeliveryApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDeliveryApp.model.DeliveryPerson;
import com.foodDeliveryApp.repository.DeliveryPersonRepository;

@Service

public final class DeliveryPersonService {
    @Autowired
    private  final DeliveryPersonRepository repository;
    //Service constructor
    public DeliveryPersonService(DeliveryPersonRepository repository) {
        this.repository = repository;
    }
    //Service used to register a DELIVERY Person or update a delivery person
    public <S extends DeliveryPerson> S save(final S entity) {
        return repository.save(entity);
    }
    //Service used to find a deliveryPerson based on his/her id
    public Optional<DeliveryPerson> findById(final Long aLong){
        return repository.findById(aLong);
    }
    //Service used to find all the deliveryPerson registered in the application
    public Iterable<DeliveryPerson> findAll(){
        return repository.findAll();
    }
    //Service used to delete deliveryPerson details based on his/her id
    
    public void deleteById(final Long aId){
        repository.deleteById(aId);
    }

}