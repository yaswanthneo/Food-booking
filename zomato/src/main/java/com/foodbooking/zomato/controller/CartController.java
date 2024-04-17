package main.java.com.foodbooking.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.foodbooking.zomato.service.CartSerive;
import com.foodbooking.zomato.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartSerive cartService;
	@Autowired
	private UserService userService;


	// PUT endpoint for adding an item to the cart
	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, FoodException, CartException, CartItemException {

		// Calls the cartService to add an item to the cart based on the request
		CartItem cart = cartService.addItemToCart(req, jwt);
		return ResponseEntity.ok(cart);

	}

	// PUT endpoint for updating cart item quantity
	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(
			@RequestBody UpdateCartItemRequest req, 
			@RequestHeader("Authorization") String jwt) throws CartItemException {
		CartItem cart = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		return ResponseEntity.ok(cart);
	}

	// DELETE endpoint for removing an item from the cart
	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException, CartException, CartItemException {
	    // Calls the cartService to remove an item from the cart
		Cart cart = cartService.removeItemFromCart(id, jwt);
		return ResponseEntity.ok(cart);

	}

	// GET endpoint for calculating the total of items in the cart
	@GetMapping("/cart/total")
	public ResponseEntity<Double> calculateCartTotals(@RequestParam Long cartId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {

		// Calls the cartService to calculate the total of items in the cart
		User user = userService.findUserProfileByJwt(jwt);
		
		Cart cart =cartService.findCartByUserId(user.getId());
		double total = cartService.calculateCartTotals(cart);
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {
User user=userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findCartByUserId(user.getId());
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> cleareCart(
			@RequestHeader("Authorization") String jwt) throws UserException, CartException {
User user=userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.clearCart(user.getId());
		return ResponseEntity.ok(cart);
	}

}
