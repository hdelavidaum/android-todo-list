package com.pucpr.todolist.model;

import java.util.ArrayList;
import java.util.UUID;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
        tasks.add(new Task("Passsar no mercado comprar comida", UUID.randomUUID(), true));
        tasks.add(new Task("Levar o totó para passear", UUID.randomUUID(), false));
        tasks.add(new Task("Comprar pneu novo para roda bike", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), false));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), false));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), false));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), false));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
        tasks.add(new Task("lavar", UUID.randomUUID(), false));
        tasks.add(new Task("lavar", UUID.randomUUID(), true));
    };

    public static DataModel getInstance(){
        return instance;
    }

    public ArrayList<Task> tasks = new ArrayList<>();
}
