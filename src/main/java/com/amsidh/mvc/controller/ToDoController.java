package com.amsidh.mvc.controller;

import com.amsidh.mvc.entities.Todo;
import com.amsidh.mvc.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public List<Todo> allTodos() {
        return toDoService.getAllTodos();
    }

    @GetMapping(value = "/{id}")
    public Todo getTodo(@PathVariable Integer id) {
        return toDoService.getTodoById(id);
    }

    @PostMapping
    public Todo getTodo(@RequestBody Todo todo) {
        return toDoService.saveTodo(todo);
    }

    @PutMapping(value = "/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        return toDoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public List<Todo> deleteByTodoById(@PathVariable Integer id) {
        toDoService.deleteById(id);
        return allTodos();
    }

}
