package com.foodDeliveryApp.service;

import java.util.List;
import java.util.Optional;

//import org.hibernate.mapping.List;
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
    public DeliveryPerson addDeliveryPerson(DeliveryPerson deliveryPerson) {
        return repository.save(deliveryPerson);
    }
    //Service used to find a deliveryPerson based on his/her id
    public DeliveryPerson getDeliveryPersonById(final Long aLong){
        Optional<DeliveryPerson> deliveryPerson = repository.findById(aLong);
        return deliveryPerson.orElse(null);
    }
    //Service used to find all the deliveryPerson registered in the application
    public List<DeliveryPerson> getAllDeliveryPersons(){
        return (List<DeliveryPerson>) repository.findAll();
    }

    //Service to update details of the deliveryPerson
    public DeliveryPerson updateDeliveryPerson(DeliveryPerson deliveryPerson){
        Optional<DeliveryPerson> optionalDeliveryPerson = repository.findById(deliveryPerson.getId());
        if(optionalDeliveryPerson.isPresent()){
            return repository.save(deliveryPerson);
        }else{
            return null;
        }

    }
    //Service used to delete deliveryPerson details based on his/her id
    
    public void deleteById(final Long aId){
        Optional<DeliveryPerson> optionalDeliveryPerson = repository.findById(aId);
        if(optionalDeliveryPerson.isPresent()){

            repository.deleteById(aId);
        }
    }

}