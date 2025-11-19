package com.todo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
