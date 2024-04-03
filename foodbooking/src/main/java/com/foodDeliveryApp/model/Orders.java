package com.foodDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordersId;

    private long restaurantId;
    private long customerId;
    private long cartId;
    private String orderStatus;
    private String delveryStatus;
    public long getOrdersId() {
        return ordersId;
    }
    public void setOrdersId(long ordersId) {
        this.ordersId = ordersId;
    }
    public long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public long getCartId() {
        return cartId;
    }
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getDelveryStatus() {
        return delveryStatus;
    }
    public void setDelveryStatus(String delveryStatus) {
        this.delveryStatus = delveryStatus;
    }
    
    
   
    
    
}
