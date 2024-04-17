package main.java.com.foodbooking.zomato.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.OrderItem;
import com.foodbooking.zomato.repository.OrderItemRepository;
@Service
public class OrderItemServiceImplementation implements OrderItemService {
	@Autowired
	 private OrderItemRepository orderItemRepository;

	    @Override
	    public OrderItem createOrderIem(OrderItem orderItem) {
	    	
	    	OrderItem newOrderItem=new OrderItem();
//	    	newOrderItem.setMenuItem(orderItem.getMenuItem());
//	    	newOrderItem.setOrder(orderItem.getOrder());
	    	newOrderItem.setQuantity(orderItem.getQuantity());
	        return orderItemRepository.save(newOrderItem);
	    }
	    





		





}
