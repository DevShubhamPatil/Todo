package com.todo.dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.todo.entities.Todo;
import com.todo.entities.User;

import jakarta.persistence.Column;

public class UserDto{

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Todo> todolist;

	public User toUser() {
		User user = new User();
		user.setId(this.getId());
		user.setFirstName(this.getFirstName());
		user.setLastName(this.getLastName());
		user.setEmail(this.getEmail());
		user.setPassword(this.getPassword());

		return user;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = null;
		this.todolist = user.getTodos();
	}

	public UserDto(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Todo> getTodolist() {
		return todolist;
	}

	public void setTodolist(List<Todo> todolist) {
		this.todolist = todolist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		System.out.println(password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password ="+ password+"]";
	}

}
