package com.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dtos.TodoRequestDto;
import com.todo.entities.Todo;
import com.todo.services.TodoService;
import com.todo.services.UserService;
import com.todo.utils.ResponseUtils;

@RestController
@RequestMapping("/todo")
public class TodoController {
	@Autowired
	UserService us;
	@Autowired
	TodoService ts;

	@GetMapping("/all")
	public ResponseEntity<?> getTodos() {

		try {
			List<Todo> todoList = ts.getAllTodos();
			return ResponseEntity.ok(todoList);
		} catch (Exception e) {
			return ResponseUtils.error(e.getMessage());
		}
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<?> getTodosByUserId(@PathVariable(name = "uid") int uid) {

		try {
			List<Todo> todoList = ts.getAllTodosByUserID(uid);
			return ResponseEntity.ok(todoList);
		} catch (Exception e) {
			return ResponseUtils.error(e.getMessage());
		}
	}

	@PostMapping("")
	public ResponseEntity<?> saveATodo(@RequestBody TodoRequestDto todoDto) {
		Todo savedTodo;
		try {
					savedTodo = ts.save(todoDto);
				}
		catch (Exception e) {
			return ResponseUtils.error(e.getMessage());
		}
		return ResponseUtils.success(savedTodo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteATodo(@PathVariable(name = "id") int id){
		
		Todo t;
		try {
			t = ts.delete(id);
		}
		catch(Exception e) {
			return ResponseUtils.error(e.getMessage());
		}

		return ResponseUtils.success(t);
	}
	@DeleteMapping("")
	public ResponseEntity<?> deleteSelectedTodos(@RequestBody List<Integer> ids) {
		
		try {
			ts.deleteByIds(ids);
		}catch(Exception e) {
			return ResponseUtils.error(e.getMessage());
		}
		return ResponseUtils.success("deletioin successful.");
	}

	@PatchMapping("")
	public ResponseEntity<?> updateATodo(@RequestBody Todo todo){
		Todo updated ;
		try {
			updated = ts.update(todo);
		}catch(Exception e) {
			return ResponseUtils.error(e.getMessage());
		}
		return ResponseUtils.success(updated);
	}
	
}
