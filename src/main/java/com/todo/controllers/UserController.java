package com.todo.controllers;

import java.util.Map;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dtos.Credentials;
import com.todo.dtos.UserDto;
import com.todo.entities.User;
import com.todo.security.jwt.JwtService;
import com.todo.services.UserService;
import com.todo.utils.ResponseUtils;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	public UserController(UserService userService, JwtService jwtService) {
		super();
		this.userService = userService;
		this.jwtService =  jwtService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginAuthentication(@RequestBody Credentials creds) {
		System.out.println(creds);
		UserDto userDto = null;
		
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword()));
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String token = jwtService.generateToken(userDetails);

		return ResponseUtils.success(Map.of("token", token));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
		UserDto responseUserDto = null;
		try {
			responseUserDto = userService.saveuser(userDto.toUser());
			if (responseUserDto == null) {
				System.out.println("inside if");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Map.of("error", "Invalid username or password"));
			}
		} catch (Exception e) {
			return ResponseUtils.error("Failed while log in attempt with message: " + e.getMessage());
		}
		return ResponseUtils.success(responseUserDto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int uid) {
		System.out.println("id id : " + uid);
		try {
			userService.DeleteUser(uid);
		} catch (Exception e) {
			return ResponseUtils.error("Failed while User delete attempt attempt with message: " + e.getMessage());
		}
		return ResponseUtils.success("User deleted successfully");
	}

}
