package com.pucpr.todolist.model;

import java.util.UUID;

public class Task {
    private String name;
    private UUID id;
    private boolean isCompleted;

    public Task(String name, UUID id, boolean isCompleted) {
        this.name = name;
        this.id = id;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
