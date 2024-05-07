package com.foodbooking.zomato.controller;


import com.foodbooking.zomato.domain.USER_ROLE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbooking.zomato.Exception.UserException;
import com.foodbooking.zomato.config.JwtProvider;
import com.foodbooking.zomato.model.Address;
import com.foodbooking.zomato.model.Cart;
// import com.foodbooking.zomato.model.PasswordResetToken;
import com.foodbooking.zomato.model.User;
import com.foodbooking.zomato.repository.CartRepository;
import com.foodbooking.zomato.repository.UserRepository;
import com.foodbooking.zomato.request.LoginRequest;
// import com.foodbooking.zomato.request.ResetPasswordRequest;
import com.foodbooking.zomato.response.ApiResponse;
import com.foodbooking.zomato.response.AuthResponse;
import com.foodbooking.zomato.service.CustomeUserServiceImplementation;
// import com.foodbooking.zomato.service.PasswordResetTokenService;
import com.foodbooking.zomato.service.UserService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")


public class AuthController {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtProvider jwtProvider;
	private CustomeUserServiceImplementation customUserDetails;
	private CartRepository cartRepository;

    // private PasswordResetTokenService passwordResetTokenService;

    private UserService userService;

	// Constructor for dependency injection
	public AuthController(UserRepository userRepository, 
			PasswordEncoder passwordEncoder, 
			JwtProvider jwtProvider,
			CustomeUserServiceImplementation customUserDetails,
			CartRepository cartRepository,
			// PasswordResetTokenService passwordResetTokenService,
			UserService userService
			) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.customUserDetails = customUserDetails;
		this.cartRepository=cartRepository;
		// this.passwordResetTokenService=passwordResetTokenService;
		this.userService=userService;

	}

	 // POST endpoint for user signup
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {

		// Extract user details from the request
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		USER_ROLE role=user.getRole();
		List<Address> addresses=user.getAddresses();
		// Check if the email is already registered
		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist!=null) {

			throw new UserException("Email Is Already Used With Another Account");
		}

		// Create new user
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setFullName(fullName);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setRole(role);
		createdUser.setAddresses(addresses);

		User savedUser = userRepository.save(createdUser);
		
		// Create a cart for the user
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		Cart savedCart = cartRepository.save(cart);
//		savedUser.setCart(savedCart);

// Authenticate the user after successful signup
		List<GrantedAuthority> authorities=new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(role.toString()));


		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password,authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		// Prepare the response
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Register Success");
		authResponse.setRole(savedUser.getRole());

		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}

	// POST endpoint for user signin
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

		// Extract username and password from the login request
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		System.out.println(username + " ----- " + password);

		// Authenticate the user
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		// Prepare the response
		AuthResponse authResponse = new AuthResponse();

		authResponse.setMessage("Login Success");
		authResponse.setJwt(token);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


		String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


		authResponse.setRole(USER_ROLE.valueOf(roleName));

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	// Helper method to authenticate the user
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// @PostMapping("/reset-password")
	//  public ResponseEntity<ApiResponse> resetPassword(
	    		
	//     		@RequestBody ResetPasswordRequest req) throws UserException {
	        
	//         PasswordResetToken resetToken = passwordResetTokenService.findByToken(req.getToken());

	//         if (resetToken == null ) {
	//         	throw new UserException("token is required...");
	//         }
	//         if(resetToken.isExpired()) {
	//         	passwordResetTokenService.delete(resetToken);
	//         	throw new UserException("token get expired...");
	        
	//         }

	        // Update user's password
	    //     User user = resetToken.getUser();
	    //     userService.updatePassword(user, req.getPassword());

	    //     // Delete the token
	    //     passwordResetTokenService.delete(resetToken);
	        
	    //     ApiResponse res=new ApiResponse();
	    //     res.setMessage("Password updated successfully.");
	    //     res.setStatus(true);

	    //     return ResponseEntity.ok(res);
	    // }
	 
	//  @PostMapping("/reset-password-request")
	//     public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) throws UserException {
	//         User user = userService.findUserByEmail(email);
	//         System.out.println("ResetPasswordController.resetPassword()");

	//         if (user == null) {
	//         	throw new UserException("user not found");
	//         }

	//         userService.sendPasswordResetEmail(user);

	//         ApiResponse res=new ApiResponse();
	//         res.setMessage("Password reset email sent successfully.");
	//         res.setStatus(true);

	//         return ResponseEntity.ok(res);
	//     }
	    
}
