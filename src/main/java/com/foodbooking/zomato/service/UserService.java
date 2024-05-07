package com.foodbooking.zomato.service;

import java.util.List;

import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.User;

import com.foodbooking.zomato.Exception.AddressException;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;

	public List<User> findAllUsers();
	
	public Address createAddress(Address address,User user)throws AddressException,UserException;

	public void deleteAddress(Long foodId) throws AddressException;

	public Address findAddressById(Long foodId) throws AddressException;

	
	public Address updateUserAddress(Long id,Address address,User user) throws UserException,AddressException;
	// void updatePassword(User user, String newPassword);

	// void sendPasswordResetEmail(User user);

}
