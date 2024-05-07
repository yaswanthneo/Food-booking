package com.foodbooking.zomato.service;

import com.stripe.exception.StripeException;
import com.foodbooking.zomato.model.Order;
import com.foodbooking.zomato.model.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
