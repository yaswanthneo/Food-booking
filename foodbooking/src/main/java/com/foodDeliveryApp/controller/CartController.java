package com.foodDeliveryApp.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.foodDeliveryApp.model.Cart;
import com.foodDeliveryApp.service.CartService;


@RestController


@RequestMapping("/api/cart")


public class CartController {
    @Autowired
    private final CartService cartService;
    //Controller constructor
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    //Adding items to cart
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart createdCart=cartService.addCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping
    @Secured("ROLE_CUSTOMER")
    //View All the items in cart
    public ResponseEntity<List<Cart>> getCart() {
        return new ResponseEntity<>(cartService.getCart(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //View details of particular item added in the cart
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart=cartService.getCartById(id);
        if(cart!=null){
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Update the item details(Quantity)
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        cart.setId(id);
        Cart updatedCart=cartService.updateCart(cart);
        if(updatedCart!=null){
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    //Delete an item added in the cart
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        cartService.deleteCartById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
