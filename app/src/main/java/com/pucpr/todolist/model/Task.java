package com.pucpr.todolist.model;

import java.util.UUID;

public class Task {
    private String name;
    private long id;
    private int isCompleted;

    public Task(String name, long id, int isCompleted) {
        this.name = name;
        this.id = id;
        this.isCompleted = isCompleted;
    }

    public Task(String name, int isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int isCompleted() {
        return isCompleted;
    }

    public void setCompleted(int completed) {
        isCompleted = completed;
    }
}
