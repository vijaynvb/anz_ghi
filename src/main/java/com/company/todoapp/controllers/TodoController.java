package com.company.todoapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.todoapp.exceptions.TodoNotFoundException;
import com.company.todoapp.models.Todo;
import com.company.todoapp.services.ITodoService;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
	
	private static final int Todo = 0;
	@Autowired
	ITodoService todoSvc;
	
	@GetMapping()
	public List<Todo> getAllTodos(){
		 
		var todos = todoSvc.getAllTodos();
		if(todos.size()==0)
			throw new TodoNotFoundException("Todo Not Found");
		else {
			for(Todo todo: todos) {
				int todoId = todo.getId();
				Link selfLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(todoId).withRel("self");
				todo.add(selfLink);
				Link deleteLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(todoId).withRel("Delete");
				todo.add(deleteLink);
				Link updateLink = WebMvcLinkBuilder.linkTo(TodoController.class).slash(todoId).withRel("update");
				todo.add(updateLink);
			}
			
			return todos;
		}
	}
	
	@GetMapping("/{id}")
	public Todo getByTodoId(@PathVariable int id){
		var todo = todoSvc.getById(id);
		if(todo == null)
			throw new TodoNotFoundException("Todo Not Found");
		else
			return todo;
	}
	
	@GetMapping("/title/{title}")
	public List<Todo> getByTodoTitle(@PathVariable String title){
		var todo = todoSvc.getByTitle(title);
		if(todo == null)
			throw new TodoNotFoundException("Todo Not Found");
		else
			return todo;
	}
	
	@PostMapping()
	public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {	// modelbinding ? spring validation framework 
		
		var newTodo = todoSvc.addTodo(todo);
		return new ResponseEntity<Todo>(newTodo, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable int id, @Valid @RequestBody Todo todo) {	// modelbinding ? spring validation framework 
		var newTodo = todoSvc.updateTodo(id, todo);
		
		if(newTodo != null)
			return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);
		else
			//return (ResponseEntity<com.company.todoapp.models.Todo>) ResponseEntity.notFound();
			throw new TodoNotFoundException("Todo Not Found");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Todo> deleteTodo(@PathVariable int id) {	// modelbinding ? spring validation framework 
		var todo = todoSvc.deleteTodo(id);
		if(todo != null)
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		else
			//return (ResponseEntity<com.company.todoapp.models.Todo>) ResponseEntity.notFound();
			throw new TodoNotFoundException("Todo Not Found");
	}
	
	// MethodArgumentNotValidException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}


