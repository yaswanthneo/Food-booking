package main.java.com.foodbooking.zomato.service;

import java.util.List;

import com.stripe.exception.StripeException;
import com.foodbooking.zomato.Exception.CartException;
import com.foodbooking.zomato.Exception.OrderException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Order;
//------------------------------------------------------------
import com.foodbooking.zomato.model.PaymentResponse;
//--------------------------------------------------------------
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateOrderRequest;

public interface OrderService {
	
	//  public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;
	public Order createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;
	 

}
