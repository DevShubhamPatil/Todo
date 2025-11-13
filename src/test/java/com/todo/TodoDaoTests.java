package com.todo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.daos.TodoDao;
import com.todo.entities.Todo;

@SpringBootTest
public class TodoDaoTests {

	@Autowired
	private TodoDao todoDao;
	
	@Test
	public void getAllByUserIDTest() {
		List<Todo> list = todoDao.findAllByUserID(1);
		list.forEach(System.out::println);
	}
	
	@Test
	public void getAllByUserIDAndTitleTest() {
		Todo todo = todoDao.findAllByUserIDAndTitle(1, "Workout session");
		assertNotNull(todo);
	}
}
