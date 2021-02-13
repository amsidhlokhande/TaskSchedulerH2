package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.entities.Todo;
import com.amsidh.mvc.exception.ExceptionModel;
import com.amsidh.mvc.exception.ToDoNotFoundException;
import com.amsidh.mvc.repositories.TodoRepository;
import com.amsidh.mvc.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.StreamSupport.stream;

@Data
@AllArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {

    private final TodoRepository todoRepository;

    @Override
    public Todo saveTodo(Todo todo) {
        return this.todoRepository.save(todo);
    }

    @Override
    public Todo getTodoById(Integer id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException(
                        new ExceptionModel(format("Todo not found with the id %d", id))));
    }

    @Override
    public Todo update(Integer id, Todo todo) {
        Todo todoById = this.getTodoById(id);
        ofNullable(todo).ifPresent(t -> {
            ofNullable(t.getUser()).ifPresent(todoById::setUser);
            ofNullable(t.getTargetDate()).ifPresent(todoById::setTargetDate);
            ofNullable(t.getDesc()).ifPresent(todoById::setDesc);
            Boolean isDone = t.getIsDone();
            if (isDone != null) todoById.setIsDone(isDone);
        });
        return this.todoRepository.save(todoById);
    }

    @Override
    public List<Todo> getAllTodos() {
        Iterable<Todo> iterable = () -> this.todoRepository.findAll().iterator();
        return stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        this.todoRepository.deleteById(id);
    }


}
