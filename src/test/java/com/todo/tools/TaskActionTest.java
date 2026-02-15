package com.todo.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskActionTest {


    @Test
    void fromString_shouldReturnAction_whenValidCommand(){
        TaskAction action = TaskAction.fromString("create");
        assertEquals(TaskAction.CREATE, action);
    }

    @Test
    void fromString_shouldIgnoreCase(){
        TaskAction action = TaskAction.fromString("CreAte");
        assertEquals(TaskAction.CREATE, action);
    }

    @Test
    void fromString_shouldThrowException_whenUnknownInput(){
        assertThrows(
                IllegalArgumentException.class,
                () -> TaskAction.fromString("unknown")
        );
    }
}
