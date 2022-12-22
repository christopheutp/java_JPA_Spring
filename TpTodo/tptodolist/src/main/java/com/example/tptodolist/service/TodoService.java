package com.example.tptodolist.service;


import com.example.tptodolist.entity.Todo;
import com.example.tptodolist.repository.impl.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository _todoRepository;

    public Todo createTodo(String title, String description) throws Exception {
        if(title == null || description == null) {
            throw new Exception("Remplir la totalité des champs");
        }
        Todo todo = new Todo(title, description);
        if(_todoRepository.create(todo)) {
            return todo;
        }
        return null;
    }

    public Todo updateTodo(int id, String title, String description) throws Exception {
        Todo todo = _todoRepository.findById(id);
        if(todo != null){
            todo.setTitle(title);
            todo.setDescription(description);
            _todoRepository.update(todo);
            return todo;
        }
        throw new Exception("Aucune todo avec cet id");
    }

    public boolean deleteTodo(int id) throws Exception {
        Todo todo = _todoRepository.findById(id);
        if(todo != null) {
            _todoRepository.delete(todo);
            return true;
        }
        throw new Exception("Aucune todo avec cet id");
    }

    public boolean updateStatus(int id) throws Exception {
        Todo todo = _todoRepository.findById(id);
        if(todo != null) {
            todo.setStatus(!todo.isStatus());
            _todoRepository.update(todo);
            return true;
        }
        throw new Exception("Aucune todo avec cet id");
    }

    public List<Todo> getByStatus(boolean status) {return _todoRepository.findByStatus(status);}

    public Todo getTodoById(int id) {return _todoRepository.findById(id);}


}
