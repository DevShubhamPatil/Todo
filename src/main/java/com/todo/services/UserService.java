package com.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.daos.UserDao;

@Service
public class UserService {
	
	@Autowired
	UserDao ud;

	
}
