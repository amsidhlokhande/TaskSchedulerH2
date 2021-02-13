package com.amsidh.mvc.service;

import com.amsidh.mvc.entities.Todo;

import java.util.List;

public interface ToDoService {

    Todo saveTodo(Todo todo);

    Todo getTodoById(Integer id);

    Todo update(Integer id, Todo todo);

    List<Todo> getAllTodos();

    void deleteById(Integer id);

}
