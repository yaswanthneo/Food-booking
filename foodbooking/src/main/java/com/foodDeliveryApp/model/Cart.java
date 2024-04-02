package com.foodDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long customerId;
    private long restaurantId;
    private long itemId;
    private String itemName;
    private Integer itemQuantity;
    private double itemPrice;
    private double totalPrice;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
    public long getItemId() {
        return itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Integer getItemQuantity() {
        return itemQuantity;
    }
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
