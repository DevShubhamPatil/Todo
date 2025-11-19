package com.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.daos.UserDao;
import com.todo.entities.User;

import jakarta.transaction.Transactional;


@SpringBootTest
public class UserDaoTest {

	private final UserDao ud;
	
	@Autowired
	public UserDaoTest(UserDao ud) {
		this.ud = ud;
		// TODO Auto-generated constructor stub
	}
	
	@Test
	@Transactional
	public void testGetUser() {
		User user = ud.findById(3).orElse(null);
		user.getTodos().forEach(System.out::println);
	}
}
