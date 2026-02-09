package com.todo.model;

public class Task {
    private final int id;
    private String title;
    private boolean complete;

    public Task(int id, String title ){
        this.id = id;
        this.title = title;
        this.complete = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString(){
        return id + " | " + title + " | " + (complete ? "[DONE]" : "[TODO]");
    }
}
