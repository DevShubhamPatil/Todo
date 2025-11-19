package com.todo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entities.Todo;

public interface TodoDao extends JpaRepository<Todo, Integer> {

	List<Todo> findAllByUserId(int userId);
	Todo findAllByUserIdAndTitle(int userId, String title);

}
