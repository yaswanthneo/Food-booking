package com.foodbooking.zomato.domain;

// Define an enum for OrderStatus with the following values
public enum OrderStatus {
    RECEIVED,   // The order has been received
    PENDING,   // The order is pending processing
    OUT_FOR_DELIVERY,  // The order is out for delivery
    DELIVERED,   // The order has been delivered
    CANCELLED    // The order has been cancelled
}
