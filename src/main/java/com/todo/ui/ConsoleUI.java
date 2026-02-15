package com.todo.ui;

import com.todo.exception.TaskNotFoundException;
import com.todo.model.Task;
import com.todo.service.TaskService;
import com.todo.tools.TaskAction;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final TaskService service;
    private final Scanner scanner= new Scanner(System.in);

    public ConsoleUI(TaskService service){
        this.service = service;
    }

    public void run(){
        while(true){
            printMenu();
            String input= scanner.nextLine().trim();
            try {
                TaskAction command = TaskAction.fromString(input);
                switch (command) {
                    case CREATE -> createTask();
                    case COMPLETE -> markTask();
                    case DELETE -> deleteTask();
                    case SHOW -> showTasks();
                    case EXIT -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                }
            }catch (IllegalArgumentException | TaskNotFoundException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    private void printMenu(){
        System.out.println("\nPlease select an action:");
        for (TaskAction cmd: TaskAction.values()){
            System.out.println("- " + cmd.getCommand());
        }
        System.out.println();

    }

    private  void createTask(){
        System.out.println("Enter task title:");
        String title = scanner.nextLine().trim();
        service.createTask(title);
        System.out.println("Task created");
    }

    private void markTask(){
        System.out.println("Enter task ID to mark as completed:");
        int id = readInt();
        service.markTaskCompleted(id);
        System.out.println("Task marked as completed.");
    }
    private void deleteTask(){
        System.out.println("Enter task ID to delete:");
        int id = readInt();
        service.deleteTask(id);
        System.out.println("Task deleted!");
    }

    private void showTasks(){
        List<Task> tasks = service.getTasks();

        if (tasks.isEmpty()){
            System.out.println("No tasks yet.");
            return;
        }

        System.out.println("----Tasks----");
        tasks.forEach(System.out::println);
    }

    private int readInt(){
        while (!scanner.hasNextInt()){
            System.out.println("Enter a valid number: ");
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
