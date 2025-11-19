package com.todo.utils;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.todo.dtos.TodoRequestDto;
import com.todo.entities.Todo;

@Mapper(componentModel = "spring")
public interface MapperUtil {
	
	Todo TodoRequestDtoToTodoEntity(TodoRequestDto todoDto);

}
