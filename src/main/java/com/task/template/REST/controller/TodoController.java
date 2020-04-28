package com.task.template.REST.controller;

import com.task.template.REST.exception.ProductNotFoundException;
import com.task.template.REST.models.Todo;
import com.task.template.REST.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoRepository repository;

    @GetMapping("/todo")
    List<Todo> All(){
        return repository.findAll();
    }

    @PostMapping("/todo")
    Todo AddNewTodo(@RequestBody Todo newTodo)
    {
        return repository.save(newTodo);
    }

    @GetMapping("/todo/{id}")
    Todo One(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }

    @PutMapping("/todo/{id}")
    Todo UpdateTodo(@RequestBody Todo newTodo, @PathVariable Long id){
        return repository.findById(id).map(todo -> {
            todo = newTodo;
            return repository.save(todo);
        }).orElseGet(() -> {
            newTodo.setId(id);
            return repository.save(newTodo);
        });
    }

    @DeleteMapping("/todo/{id}")
    void deleteTodo(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/todo")
    void deleteAll(){
        repository.deleteAll();
    }
}
