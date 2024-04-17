package main.java.com.foodbooking.zomato.controller;

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
@RequestMapping("/api")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
    @PostMapping("/order")
	// public ResponseEntity<PaymentResponse>  createOrder(@RequestBody CreateOrderRequest order,
	// 		@RequestHeader("Authorization") String jwt) 
	// 				throws UserException, RestaurantException, 
	// 				CartException, 
	// 				StripeException,
	// 				OrderException{
	public ResponseEntity<Order>  createOrder(@RequestBody CreateOrderRequest order,
		@RequestHeader("Authorization") String jwt) 
				throws UserException, RestaurantException, 
				CartException, 
				StripeException,
				OrderException{
		User user=userService.findUserProfileByJwt(jwt);
		System.out.println("req user "+user.getEmail());
    	if(order!=null) {
			
			// PaymentResponse res = orderService.createOrder(order,user);
			Order res = orderService.createOrder(order,user);
			if(res.getTotalItem()==0){
				throw new OrderException("Cart is empty");
			}
			return ResponseEntity.ok(res);
			
    	}else throw new OrderException("Please provide valid request body");
		
    }
    
 
    
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(	@RequestHeader("Authorization") String jwt) throws OrderException, UserException{

		// This method handles GET requests to "/api/order/user" for fetching all orders of a user.

		// Calls the userService to find the user profile by JWT token.

    	User user=userService.findUserProfileByJwt(jwt);
    	
		// Checks if the user ID is not null.
    	if(user.getId()!=null) {
			// Calls the orderService to get all orders of the user.
			List<Order> userOrders = user.getOrders();
			return ResponseEntity.ok(userOrders);
    	}else {
			// Returns a bad request status if the user ID is null
    		return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
    	}
    }
    

    

	
}
