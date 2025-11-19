package com.todo.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime created_date;
	@UpdateTimestamp
	@Column(name = "last_updated_date")
	private LocalDateTime last_updated_date;
	@Column(name = "target_date")
	private LocalDateTime target_date;

	@ManyToOne
	@JoinColumn(name = "userID")
	@JsonIgnore
	private User user;

	public Todo() {
		super();
		System.out.println("TODO Constructor 0 used");
		// TODO Auto-generated constructor stub
	}

	public Todo(int id, int userID, String title, String description, LocalDateTime created_date,
			LocalDateTime last_updated_date, LocalDateTime target_date) {
		super();
		System.out.println("TODO Constructor 1 used");
		this.id = id;
		this.title = title;
		this.description = description;
		this.created_date = created_date;
		this.last_updated_date = last_updated_date;
		this.target_date = target_date;
		this.user = new User(userID);
	}

	public Todo(int id, int userID, String title, String description) {
		super();
		System.out.println("TODO Constructor 2 used");
		this.id = id;
		this.user = new User(userID);
		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", user= " + user + ", title=" + title + ", description=" + description
				+ ", created_date=" + created_date + ", last_updated_date=" + last_updated_date + ", target_date="
				+ target_date + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Todo) {
			Todo t = (Todo) obj;
			if (t.getUser().getId() == this.getUser().getId() && t.getTitle().equalsIgnoreCase(this.getTitle()))
				return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		System.out.println("TODO setUser used");
		this.user = user;
	}

	public String getTitle() {
		System.out.println("TODO setTitle  used");
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public LocalDateTime getLast_updated_date() {
		return last_updated_date;
	}

	public void setLast_updated_date(LocalDateTime last_updated_date) {
		this.last_updated_date = last_updated_date;
	}

	public LocalDateTime getTarget_date() {
		return target_date;
	}

	public void setTarget_date(LocalDateTime target_date) {
		this.target_date = target_date;
	}

}
