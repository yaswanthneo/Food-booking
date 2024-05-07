package com.foodbooking.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.foodbooking.zomato.Exception.CartException;
import com.foodbooking.zomato.Exception.OrderException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Order;
import com.foodbooking.zomato.model.PaymentResponse;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.CreateOrderRequest;
import com.foodbooking.zomato.service.OrderService;
import com.foodbooking.zomato.service.UserService;

@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	// API endpoint for deleting an order by ID
    @DeleteMapping("/deleteorder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException{
    	if(orderId!=null) {
    		orderService.cancelOrder(orderId);
    	return ResponseEntity.ok("Order deleted with id "+orderId);
    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }
    
    // API endpoint for getting all orders of a restaurant
    @GetMapping("/restaurantorders/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(
    		@PathVariable Long restaurantId,
    		@RequestParam(required = false) String order_status) throws OrderException, RestaurantException{
    	
    		List<Order> orders = orderService.getOrdersOfRestaurant(restaurantId,order_status);
    		
//    		System.out.println("ORDER STATUS----- "+orderStatus);
    		return ResponseEntity.ok(orders);
    		
    	
    	
    }
    

	// API endpoint for updating the status of an order

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws OrderException, RestaurantException{
    	
    		Order orders = orderService.updateOrder(orderId, orderStatus);
    		return ResponseEntity.ok(orders);
    		
    }

}
