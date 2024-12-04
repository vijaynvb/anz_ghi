package com.company.todoapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.todoapp.controllers.TodoController;
import com.company.todoapp.exceptions.TodoNotFoundException;
import com.company.todoapp.models.Todo;
import com.company.todoapp.services.ITodoService;

public class TodoappApplicationTests {

    @Mock
    private ITodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        // Arrange
        List<Todo> todos = Arrays.asList(new Todo(), new Todo());
        when(todoService.getAllTodos()).thenReturn(todos);

        // Act
        List<Todo> result = todoController.getAllTodos();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetByTodoId() {
        // Arrange
        Todo todo = new Todo();
        todo.setId(1);
        when(todoService.getById(1)).thenReturn(todo);

        // Act
        Todo result = todoController.getByTodoId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetByTodoTitle() {
        // Arrange
        String title = "Test";
        List<Todo> todos = Arrays.asList(new Todo(), new Todo());
        when(todoService.getByTitle(title)).thenReturn(todos);

        // Act
        List<Todo> result = todoController.getByTodoTitle(title);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateTodo() {
        // Arrange
        Todo todo = new Todo();
        when(todoService.addTodo(any(Todo.class))).thenReturn(todo);

        // Act
        ResponseEntity<Todo> responseEntity = todoController.createTodo(todo);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateTodo() {
        // Arrange
        Todo todo = new Todo();
        todo.setId(1);
        when(todoService.updateTodo(eq(1), any(Todo.class))).thenReturn(todo);

        // Act
        ResponseEntity<Todo> responseEntity = todoController.updateTodo(1, todo);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteTodo() {
        // Arrange
        Todo todo = new Todo();
        todo.setId(1);
        when(todoService.deleteTodo(1)).thenReturn(todo);

        // Act
        ResponseEntity<Todo> responseEntity = todoController.deleteTodo(1);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testTodoNotFoundException() {
        // Arrange
        doThrow(TodoNotFoundException.class).when(todoService).getById(anyInt());

        // Act & Assert
        assertThrows(TodoNotFoundException.class, () -> {
            todoController.getByTodoId(1);
        });
    }
}

