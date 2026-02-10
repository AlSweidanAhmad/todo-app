package com.todo.service;

import com.todo.exception.TaskNotFoundException;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final TaskRepository repository;
    private int nextId = 1;
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String title){
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be empty");

        Task task = new Task(nextId++, title);
        return repository.save(task);
    }

    public void deleteTask(int id){
        if(!repository.remove(id)) throw  new TaskNotFoundException(id);
    }

    public List<Task> getTasks(){
        return new ArrayList<>(repository.findAll());
    }

    public void markTaskCompleted(int id){
        Task task = repository.findById(id);
        if(task == null) throw new TaskNotFoundException(id);
        if(task.isComplete()) {
            return;
        }

        task.setComplete(true);
    }



}
