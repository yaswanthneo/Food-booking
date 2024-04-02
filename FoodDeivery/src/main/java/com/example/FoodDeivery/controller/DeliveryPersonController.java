package com.example.FoodDeivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.FoodDeivery.model.DeliveryPerson;
import com.example.FoodDeivery.service.DeliveryPersonService;



@RestController


@RequestMapping("/api/delivery")

public class DeliveryPersonController {
    @Autowired
    private final DeliveryPersonService deliveryService;

    //Controller constructor
    public DeliveryPersonController(DeliveryPersonService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @Secured("ROLE_DELIVERYPERSON")
    //Create the deliveryPerson details 
    public ResponseEntity<DeliveryPerson> addDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        DeliveryPerson createdDeliveryPerson = deliveryService.addDeliveryPerson(deliveryPerson);
        return new ResponseEntity<DeliveryPerson>(createdDeliveryPerson, HttpStatus.CREATED);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    //Get all the deliveryPersons registered in the application
    public ResponseEntity<List<DeliveryPerson>> getAllDeliveryPersons(){
        return new  ResponseEntity<>(deliveryService.getAllDeliveryPersons(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_DELIVERYPERSON"})
    //Get a deliveryperson registered in the application based on his/her id
    public ResponseEntity<DeliveryPerson> getDeliveryPersonById(@PathVariable Long id) {
        DeliveryPerson deliveryPerson = deliveryService.getDeliveryPersonById(id);
        if(deliveryPerson != null){
            return new ResponseEntity<>(deliveryPerson, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_DELIVERYPERSON")
    //Update the deliveryPerson details
    public ResponseEntity<DeliveryPerson> updatedDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson deliveryPerson) {
        deliveryPerson.setId(id);
        DeliveryPerson updatedCustomer = deliveryService.updateDeliveryPerson(deliveryPerson);
        if(updatedCustomer != null){
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_DELIVERYPERSON"})
    //Delete a deliveryPerson details based on his/her id
    public ResponseEntity<Void> deleteDeliveryPerson(@PathVariable("id") Long id) {
        deliveryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}