package com.todo;

import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import com.todo.ui.ConsoleUI;

public class App {
    public static void main(String[] args){
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        ConsoleUI ui = new ConsoleUI(service);
        ui.run();
    }
}
