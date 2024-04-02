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

import com.foodDeliveryApp.model.DeliveryPerson;
import com.foodDeliveryApp.service.DeliveryPersonService;

@RestController


@RequestMapping("/api/delivery")

public class DeliveryPersonController {
    private final DeliveryPersonService deliveryService;
   // private DeliveryPersonService deliveryService;
    //Controller constructor
    public DeliveryPersonController(DeliveryPersonService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @Secured("ROLE_DELIVERYPERSON")
    //Register the deliveryPerson details 
    public DeliveryPerson saveDeliveryPerson(@RequestBody DeliveryPerson delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    //Get all the deliveryPersons registered in the application
    public Iterable<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_DELIVERYPERSON"})
    //Get a deliveryperson registered in the application based on his/her id
    public Optional<DeliveryPerson> getDeliveryPersonById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_DELIVERYPERSON")
    //Update the deliveryPerson details
    public DeliveryPerson updatedDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson deliveryPerson) {
        deliveryPerson.setId(id);
        return deliveryService.save(deliveryPerson);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_DELIVERYPERSON"})
    //Delete a deliveryPerson details based on his/her id
    public void deleteDeliveryPerson(@PathVariable("id") Long id) {
        deliveryService.deleteById(id);
    }



}

