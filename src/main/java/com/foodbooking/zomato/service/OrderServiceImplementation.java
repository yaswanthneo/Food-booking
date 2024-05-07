package com.foodbooking.zomato.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.foodbooking.zomato.Exception.CartException;
import com.foodbooking.zomato.Exception.OrderException;
import com.foodbooking.zomato.Exception.RestaurantException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.Cart;
import com.foodbooking.zomato.model.CartItem;
// import com.foodbooking.zomato.model.Notification;
import com.foodbooking.zomato.model.Order;
import com.foodbooking.zomato.model.OrderItem;
import com.foodbooking.zomato.model.PaymentResponse;
import com.foodbooking.zomato.model.Restaurant;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.repository.AddressRepository;
import com.foodbooking.zomato.repository.CartRepository;
import com.foodbooking.zomato.repository.OrderItemRepository;
import com.foodbooking.zomato.repository.OrderRepository;
import com.foodbooking.zomato.repository.RestaurantRepository;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.request.CreateOrderRequest;
@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CartSerive cartService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentService paymentSerive;
	
	

	@Override
	// public PaymentResponse createOrder(CreateOrderRequest order,User user) throws UserException, RestaurantException, CartException, StripeException {
	public Order createOrder(CreateOrderRequest order,User user) throws UserException, RestaurantException, CartException, StripeException {
		
	    Long shippAddressId = order.getAddressId();

	  
	    Optional<Address> optionalAddress= addressRepository.findById(shippAddressId);
	    
		Address deliveryAddress=optionalAddress.get();
	    // if(!user.getAddresses().contains(shippAddressId)) {
	    // 	user.getAddresses().add(savedAddress);
	    // }
	    
		
		// System.out.println("user addresses --------------  "+user.getAddresses());
		   
		//  userRepository.save(user);
	    
	    Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
	    if(restaurant.isEmpty()) {
	    	throw new RestaurantException("Restaurant not found with id "+order.getRestaurantId());
	    }
	    
	    Order createdOrder = new Order();
	    
	    createdOrder.setCustomer(user);
	    createdOrder.setDeliveryAddress(deliveryAddress);
	    createdOrder.setOrderStatus("PENDING");
	    createdOrder.setRestaurant(restaurant.get());

        Cart cart = cartService.findCartByUserId(user.getId());
        
	    List<OrderItem> orderItems = new ArrayList<>();
	    int totalItem=0;
	    for (CartItem cartItem : cart.getItems()) {
			totalItem+=1;
	        OrderItem orderItem = new OrderItem();
	       orderItem.setFood(cartItem.getFood());
	       orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setTotalPrice(cartItem.getFood().getPrice()* cartItem.getQuantity());

	        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
	        orderItems.add(savedOrderItem);
	    }
   
	     Long totalPrice = cartService.calculateCartTotals(cart);

	    createdOrder.setTotalPrice(totalPrice);
	    createdOrder.setRestaurant(restaurant.get());
		
		createdOrder.setTotalItem(totalItem);
	    createdOrder.setItems(orderItems);
	    Order savedOrder = orderRepository.save(createdOrder);
		
		user.getOrders().add(savedOrder);
		userRepository.save(user);
	   restaurant.get().getOrders().add(savedOrder);
	   
	   restaurantRepository.save(restaurant.get());
	//    Cart cleared=cartService.clearCart(user.getId());
	//    PaymentResponse res=paymentSerive.generatePaymentLink(savedOrder);
	//    return res;
	return savedOrder;

	}

	@Override
	public void cancelOrder(Long orderId) throws OrderException {
           Order order =findOrderById(orderId);
           if(order==null) {
        	   throw new OrderException("Order not found with the id "+orderId);
           }
		
		    orderRepository.deleteById(orderId);
		
	}
	
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) return order.get();
		
		throw new OrderException("Order not found with the id "+orderId);
	}

	@Override
	public List<Order> getUserOrders(Long userId) throws OrderException {
		List<Order> orders=orderRepository.findAllUserOrders(userId);
		return orders;
	} 

	@Override
	public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException {
		
			List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
			
			if(orderStatus!=null) {
				orders = orders.stream()
						.filter(order->order.getOrderStatus().equals(orderStatus))
						.collect(Collectors.toList());
			}
			
			return orders;
	}
//    private List<MenuItem> filterByVegetarian(List<MenuItem> menuItems, boolean isVegetarian) {
//    return menuItems.stream()
//            .filter(menuItem -> menuItem.isVegetarian() == isVegetarian)
//            .collect(Collectors.toList());
// }
	
	

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
		Order order=findOrderById(orderId);
		
		System.out.println("--------- "+orderStatus);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING") || orderStatus.equals("RECEIVED")) {
			order.setOrderStatus(orderStatus);
			// Notification notification=notificationService.sendOrderStatusNotification(order);
			return orderRepository.save(order);
		}
		else throw new OrderException("Please Select A Valid Order Status");
		
		
	}
	
	

}
