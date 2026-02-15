package com.todo.tools;

import java.util.Arrays;

public enum TaskAction {
    CREATE("create"),
    DELETE("delete"),
    SHOW("show"),
    COMPLETE("complete"),
    EXIT("exit");

    private final String command;

    TaskAction(String command){
        this.command = command;
    }

    public static TaskAction fromString(String input){
        if(input ==  null){
            throw new IllegalArgumentException("Action input must not be null");
        }

        String normalized = input.trim();

        if(normalized.isEmpty()){
            throw new IllegalArgumentException("Action input must not be Blank");
        }

        return Arrays.stream(values())
                .filter(a -> a.command.equalsIgnoreCase(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown action: " + input));
    }
}
