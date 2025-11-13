package com.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.daos.TodoDao;
import com.todo.entities.Todo;

@Service
public class TodoService {

	@Autowired
	private TodoDao td;

	public List<Todo> getAllTodos() {

		List<Todo> list = td.findAll();
		if (!list.isEmpty())
			return list;
		return null;
	}

	public Todo save(Todo t) throws Exception {
		Todo availableTodo = td.findAllByUserIDAndTitle(t.getUserID(), t.getTitle());
		if (availableTodo != null)
			throw new Exception("Todo For Current user with title: ** " + t.getTitle()
					+ " ** Already Exists. please create Unique todo");
		Todo st = td.save(t);
		if (st != null)
			return st;
		return null;
	}

	public Todo delete(int tId) {
		Optional<Todo> t = td.findById(tId);
		if (t.isPresent()) {
			td.delete(t.get());
			return t.get();
		} else
			return null;
	}
	
	public void deleteByIds(List<Integer> ids) {
		td.deleteAllById(ids);
	}
	
	public Todo update(Todo t) throws Exception {
		Optional<Todo> oTodo = td.findById(t.getId());
		if(oTodo.isPresent()) {
			Todo t1 =  oTodo.get();
			if(t.getTitle() != null)
			t1.setTitle(t.getTitle());
			t1.setDescription(t.getDescription());
			if(t.getTarget_date() != null)
			t1.setTarget_date(t.getTarget_date());
			return td.save(t1);
		}
		else
			throw new Exception("No TODO Entity found to update, ID: "+t.getId());
		
	}

}
