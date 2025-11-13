package com.todo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entities.Todo;

public interface TodoDao extends JpaRepository<Todo, Integer> {

	List<Todo> findAllByUserID(int userID);
	Todo findAllByUserIDAndTitle(int userID, String title);

}
