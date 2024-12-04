package com.company.todoapp.services;

import java.util.List;

import com.company.todoapp.models.Todo;

public interface ITodoService {

	
	public List<Todo> getAllTodos();
	
	public Todo getById(int id);
	
	public List<Todo> getByTitle(String title);
	
	public Todo addTodo(Todo todo);
	
	public Todo updateTodo(int id, Todo todo);
	
	public Todo deleteTodo(int id);
	
}
