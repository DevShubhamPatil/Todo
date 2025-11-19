package com.todo.dtos;

import java.time.LocalDateTime;

public class TodoRequestDto {

	private int id;
	private int userID;
	private String title;
	private String description;
	private LocalDateTime target_date;
	
	public TodoRequestDto(int id, int userID, String title, String description, LocalDateTime target_date) {
		super();
		this.id = id;
		this.userID = userID;
		this.title = title;
		this.description = description;
		this.target_date = target_date;
	}
	
	public TodoRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TodoRequest [id=" + id + ", userID=" + userID + ", title=" + title + ", description=" + description
				+ ", target_date=" + target_date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTitle() {
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

	public LocalDateTime getTarget_date() {
		return target_date;
	}

	public void setTarget_date(LocalDateTime target_date) {
		this.target_date = target_date;
	}
	
	
	
}
