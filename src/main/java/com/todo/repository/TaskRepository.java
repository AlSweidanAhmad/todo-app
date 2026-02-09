package com.todo.repository;

import com.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public Task save(Task task){
        tasks.add(task);
        return task;
    }

    public Task findById(int id){
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean remove(int id){
        return tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> findAll(){
        return new ArrayList<>(tasks);
    }
}
