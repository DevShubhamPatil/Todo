package com.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todo.daos.TodoDao;
import com.todo.daos.UserDao;
import com.todo.dtos.TodoRequestDto;
import com.todo.entities.Todo;
import com.todo.entities.User;
import com.todo.utils.MapperUtil;

@Service
public class TodoService {

	@Autowired
	private TodoDao td;
	@Autowired
	private UserDao ud;
	@Autowired
	private MapperUtil mapper;
	public List<Todo> getAllTodos() {

		List<Todo> list = td.findAll();
		if (!list.isEmpty())
			return list;
		return null;
	}

	public List<Todo> getAllTodosByUserID(int uid) {

		List<Todo> list = td.findAllByUserId(uid);
		if (!list.isEmpty())
			return list;
		return null;
	}

	public Todo save(TodoRequestDto t) throws Exception {
		Todo availableTodo = td.findAllByUserIdAndTitle(t.getUserID(), t.getTitle());
		if (availableTodo != null) {
			System.out.println("throwing exception ");
			throw new Exception("Todo For Current user with title: ** " + t.getTitle()
					+ " ** Already Exists. please create Unique todo");
		}
		Todo todo = mapper.toTodoEntity(t);
		System.out.println("todo from mapper is\n "+todo);
		
		Optional<User> userOptional = ud.findById(t.getUserID());
		if(userOptional.isPresent())
			todo.setUser(userOptional.get());
		else
			throw new Exception("no user exists with id: " + t.getUserID());
		Todo st = td.save(todo);
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
		if (oTodo.isPresent()) {
			Todo t1 = oTodo.get();
			if (t.getTitle() != null)
				t1.setTitle(t.getTitle());
			t1.setDescription(t.getDescription());
			if (t.getTarget_date() != null)
				t1.setTarget_date(t.getTarget_date());
			return td.save(t1);
		} else
			throw new Exception("No TODO Entity found to update, ID: " + t.getId());

	}
	
	public Page<TodoRequestDto> getAllTodos(Pageable p){
		Page<Todo> page = td.findAll(p);
		Page<TodoRequestDto> list = page.map(todo -> mapper.toTodoRequestDto(todo) );
		
		return list;
	}

}
