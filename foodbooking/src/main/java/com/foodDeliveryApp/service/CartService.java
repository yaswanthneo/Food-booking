package com.foodDeliveryApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDeliveryApp.model.Cart;

import com.foodDeliveryApp.repository.CartRepository;


@Service


public class CartService {
      @Autowired
    private CartRepository cartRepository;
    //Service constructor
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    //Service used to add an item to the cart
    @SuppressWarnings("null")
	public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
    //Service used to view details of an item in the cart
    public Cart getCartById(final Long aLong){
        Optional<Cart> cart=cartRepository.findById(aLong);
        return cart.orElse(null);
    }
    //Service used to view all the items in the cart

    public List<Cart> getCart(){
        return (List<Cart>) cartRepository.findAll();
    }
    //Service to update items in cart(Quantity)
    public Cart updateCart(Cart cart){
        Optional<Cart> optionalCart=cartRepository.findById(cart.getCartId());
        if(optionalCart.isPresent()){
            return cartRepository.save(cart);
        }else{
            return null;
        }
    }

    //Service used to delete a cart item
    
    public void deleteCartById(final Long aId){
        Optional<Cart> optionalCart=cartRepository.findById(aId);
        if(optionalCart.isPresent()){
            cartRepository.deleteById(aId);
        }
    }

}
