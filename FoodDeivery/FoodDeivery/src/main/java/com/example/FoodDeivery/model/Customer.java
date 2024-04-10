package com.example.FoodDeivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String customerName;
   
    private long customerMobile;
    private String customerAddress;

    //Get Id of the customer
    public long getId() {
        return id;
    }
    //Set or change the id of the customer
    public void setId(long id) {
        this.id = id;
    }

    // Get the customer's name
    public String getCustomerName() {
        return customerName;
    }
    //Set or change the customer name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    //Get the customer's mobile number
    public long getCustomerMobile() {
        return customerMobile;
    }

    //Set or change the customer's mobile number
    public void setCustomerMobile(long customerMobile) {
        this.customerMobile = customerMobile;
    }

    //Get the customer address
    public String getCustomerAddress() {
        return customerAddress;
    }

    //Set or change the customer address
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    
    
    
}