package com.todo.service;

import com.todo.exception.TaskNotFoundException;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService service;
    private TaskRepository repository;

    @BeforeEach
    void setUp(){
        repository = new TaskRepository();
        service = new TaskService(repository);
    }

    @Test
    void createTask_shouldCreateTask(){

        //Arrange
        String title = "First Task";

        //Act
        service.createTask(title);
        Task found = repository.findById(1);

        //Assert
        assertNotNull(found);
        assertEquals(1, found.getId());
        assertEquals(title ,found.getTitle());

    }

    @Test
    void createTask_shouldAssignSequentialIds(){

        //Arrange & Act
        service.createTask("Task 1");
        service.createTask("Task 2");

        Task firstTask = repository.findById(1);
        Task secondTask = repository.findById(2);

        //Assert
        assertNotNull(firstTask);
        assertNotNull(secondTask);
        assertEquals(1, firstTask.getId());
        assertEquals(2, secondTask.getId());
    }

    @Test
    void createTask_shouldThrowException_whenTitleIsBlank(){

        //Arrange
        String blankTitle = " ";

        //Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> service.createTask(blankTitle)
        );
    }

    @Test
    void deleteTask_shouldDeleteExistingTask(){

        //Arrange
        service.createTask("Delete Task");

        //Act
        service.deleteTask(1);

        //Assert
        assertNull(repository.findById(1));
    }

    @Test
    void deleteTask_shouldThrowException_whenTaskDoesNotExist(){

        //Act & Assert
        assertThrows(
                TaskNotFoundException.class,
                () -> service.deleteTask(999)
        );
    }

    @Test
    void markTaskCompleted_shouldMarkTaskAsCompleted(){
        //Arrange
        service.createTask("Mark Task as completed");

        //Act
        service.markTaskCompleted(1);
        Task completedTask = repository.findById(1);

        //Assert
        assertNotNull(completedTask);
        assertTrue(completedTask.isComplete());

    }

}
