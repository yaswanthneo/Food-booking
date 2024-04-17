package main.java.com.foodbooking.zomato.service;

import com.foodbooking.zomato.Exception.CartException;
import com.foodbooking.zomato.Exception.CartItemException;
import com.foodbooking.zomato.Exception.FoodException;
import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Cart;
import com.foodbooking.zomato.model.CartItem;
import com.foodbooking.zomato.model.Food;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.request.AddCartItemRequest;
import com.foodbooking.zomato.request.UpdateCartItemRequest;

public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	
	public Cart clearCart(Long userId) throws CartException, UserException;
	

	

}
