package com.pucpr.todolist.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
//        addTask(new Task("Primeira Task", 0));
    };

    public static DataModel getInstance(){
        return instance;
    }
    private ArrayList<Task> tasks;
    private TaskDatabase database;

    public void createDataBase(Context c){
        database = new TaskDatabase(c);
        tasks = database.getTasksFromDB();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int idx){
        return tasks.get(idx);
    }

    public int getTaskListSize(){
        return tasks.size();
    }

    public boolean addTask(Task t){
        long id = database.createTaskInDB(t);
        if (id > 0){
            t.setId(id);
            tasks.add(t);
            return true;
        }
        return false;
    }

    public boolean updateTask(Task t, int idx){
        int count = database.updateTaskInDB(t);
        if (count == 1){
            tasks.set(idx, t);
            return true;
        }
        return false;
    }

    public boolean deleteTask(int idx){
        int count = database.removeTaskInDB(getTask(idx));
        if (count == 1){
            tasks.remove(idx);
            return true;
        }
        return false;
    }
}
