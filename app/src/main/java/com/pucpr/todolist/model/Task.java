package com.pucpr.todolist.model;

import java.util.UUID;

public class Task {
    private String name;
    private int id;
    private int isCompleted;

    public Task(String name, int id, int isCompleted) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int isCompleted() {
        return isCompleted;
    }

    public void setCompleted(int completed) {
        isCompleted = completed;
    }
}
