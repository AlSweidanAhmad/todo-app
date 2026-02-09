package com.todo.repository;

import com.todo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {
    private TaskRepository repository;

    @BeforeEach
    void setUp(){
        repository = new TaskRepository();
    }

    @Test
    void save_shouldStoreTask(){

        //Arrange
        Task task = new Task(1, "Submit project report");

        //Act
        repository.save(task);

        //Assert
        Task found = repository.findById(1);

        assertNotNull(found);
        assertEquals("Submit project report", found.getTitle());
    }

    @Test
    void findById_shouldReturnNull_whenTaskDoesNotExist(){

        //Act
        Task result = repository.findById(404);

        //Assert
        assertNull(result);
    }

    @Test
    void remove_shouldDeleteExistingTask(){

        //Arrange
        Task task = new Task(1, "Schedule dentist appointment");
        repository.save(task);

        //Act
        boolean removed = repository.remove(1);

        //Assert
        assertTrue(removed);
        assertNull(repository.findById(1));
    }

    @Test
    void findAll_shouldReturnDefensiveCopy_notInternalList(){

        //Arrange
        repository.save(new Task(1, "Prepare meeting notes"));

        //Act
        List<Task> tasks = repository.findAll();
        tasks.clear();

        //Assert
        assertEquals(1, repository.findAll().size());
    }
}
