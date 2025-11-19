package com.todo.controllers;

import java.util.Map;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dtos.Credentials;
import com.todo.dtos.UserDto;
import com.todo.services.UserService;
import com.todo.utils.ResponseUtils;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public ResponseEntity<?> loginAuthentication(@RequestBody Credentials creds) {
		System.out.println(creds);
		UserDto userDto = null;
		try {
			userDto = userService.authenticateLogin(creds);
			System.out.println(userDto);
			if (userDto == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Map.of("error", "Invalid username or password"));
			}
		} catch (Exception e) {
			return ResponseUtils.error("Failed while log in attempt with message: " + e.getMessage());
		}
		return ResponseUtils.success(userDto);

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
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int uid){
		System.out.println("id id : "+uid);
		try {
			userService.DeleteUser(uid);
		}
		catch(Exception e) {
			return ResponseUtils.error("Failed while User delete attempt attempt with message: " + e.getMessage());
		}
		return ResponseUtils.success("User deleted successfully");
	}
 
}
