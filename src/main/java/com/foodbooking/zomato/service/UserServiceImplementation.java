package com.foodbooking.zomato.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.config.JwtProvider;
import com.foodbooking.zomato.domain.USER_ROLE;
import com.foodbooking.zomato.model.Address;
// import com.foodbooking.zomato.model.PasswordResetToken;
import com.foodbooking.zomato.model.User;
// import com.foodbooking.zomato.repository.PasswordResetTokenRepository;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.repository.AddressRepository;
import com.foodbooking.zomato.Exception.AddressException;


/**
 * UserServiceImplementation provides the implementation for UserService.
 * It handles user-related operations such as finding user profiles, creating addresses, and more.
 */



@Service
public class UserServiceImplementation implements UserService {


	private UserRepository userRepository;
	private AddressRepository addressRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	// private PasswordResetTokenRepository passwordResetTokenRepository;
	private JavaMailSender javaMailSender;
	
	public UserServiceImplementation(
			UserRepository userRepository,
			JwtProvider jwtProvider,
			PasswordEncoder passwordEncoder, AddressRepository addressRepository){
			// PasswordResetTokenRepository passwordResetTokenRepository,
			//JavaMailSender javaMailSender) {
		
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
		this.passwordEncoder=passwordEncoder;
		this.addressRepository=addressRepository;
		// this.passwordResetTokenRepository=passwordResetTokenRepository;
		//this.javaMailSender=javaMailSender;
		
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
//		System.out.println("email user "+user.get().getEmail());
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		List<User> allUsers=userRepository.findAll();
		return allUsers.stream()
		.filter(user -> user.getRole() == USER_ROLE.ROLE_CUSTOMER)
		.collect(Collectors.toList());
	}

	
	
	// @Override
    // public void updatePassword(User user, String newPassword) {
    //     user.setPassword(passwordEncoder.encode(newPassword));
    //     userRepository.save(user);
    // }

	// @Override
	// public void sendPasswordResetEmail(User user) {
		
	// 	// Generate a random token (you might want to use a library for this)
    //     String resetToken = generateRandomToken();
        
    //     // Calculate expiry date
    //     Date expiryDate = calculateExpiryDate();

    //     // Save the token in the database
    //     // PasswordResetToken passwordResetToken = new PasswordResetToken(resetToken,user,expiryDate);
    //     // passwordResetTokenRepository.save(passwordResetToken);

    //     // Send an email containing the reset link
    //     sendEmail(user.getEmail(), "Password Reset", "Click the following link to reset your password: http://localhost:3000/account/reset-password?token=" + resetToken);
	// }
	// private void sendEmail(String to, String subject, String message) {
	//     SimpleMailMessage mailMessage = new SimpleMailMessage();

	//     mailMessage.setTo(to);
	//     mailMessage.setSubject(subject);
	//     mailMessage.setText(message);

	//     javaMailSender.send(mailMessage);
	// }
	// private String generateRandomToken() {
	//     return UUID.randomUUID().toString();
	// }
	// private Date calculateExpiryDate() {
    //     Calendar cal = Calendar.getInstance();
    //     cal.setTime(new Date());
    //     cal.add(Calendar.MINUTE, 10);
    //     return cal.getTime();
    // }
	
	@Override
	public User findUserByEmail(String username) throws UserException {
		
		User user=userRepository.findByEmail(username);
		
		if(user!=null) {
			
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}

	@Override
	public Address createAddress(Address address,User user)
			throws AddressException,
	UserException {
			
			Address savedAddress=addressRepository.save(address);
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
			
			return savedAddress;
		
	}

	@Override
	public void deleteAddress(Long addressId) throws AddressException {
		Address address=findAddressById(addressId);
		
//		foodRepository.save(food);
		addressRepository.delete(address);

	}

	public Address updateUserAddress(Long id,Address address,User user) throws UserException,AddressException{
		Address newAddress=findAddressById(id);
		user.getAddresses().remove(newAddress);
		newAddress.setCity(address.getCity());
		newAddress.setFullName(address.getFullName());
		newAddress.setMobileNo(address.getMobileNo());
		newAddress.setHouseNo(address.getHouseNo());
		newAddress.setLandmark(address.getLandmark());
		newAddress.setPostalCode(address.getPostalCode());
		newAddress.setState(address.getState());
		newAddress.setStreetAddress(address.getStreetAddress());
		Address savedAddress=addressRepository.save(newAddress);
		user.getAddresses().add(savedAddress);
		userRepository.save(user);
		return savedAddress;
	}

	@Override
	public Address findAddressById(Long addressId) throws AddressException {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isPresent()) {
			return address.get();
		}
		throw new AddressException("Address with id" + addressId + "not found");
	}

}
