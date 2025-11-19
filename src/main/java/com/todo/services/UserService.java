package com.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.daos.UserDao;
import com.todo.dtos.Credentials;
import com.todo.dtos.UserDto;
import com.todo.entities.User;

@Service
public class UserService {
	
	@Autowired
	UserDao ud;
	
	public UserDto getUserByEmail(String email) {
		User user = ud.findByEmail(email);
		return new UserDto(user);
	}
	
	public UserDto authenticateLogin(Credentials creds) {
		User user = ud.findByEmail(creds.getEmail());
		if (user != null && user.getPassword().equals(creds.getPassword())) {
			return new UserDto(user);
		}
		else {
			return null;
		}
	}
	
	public UserDto saveuser(User user) {
		User savedUser = ud.save(user);
		return new UserDto(savedUser);
	}
	
	public User updateUserDetails(User user) {
		return ud.save(user);
	}
	
	public void DeleteUser(int uid) {
		ud.deleteById(uid);
	}




}
