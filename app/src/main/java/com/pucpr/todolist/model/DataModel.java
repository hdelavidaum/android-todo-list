package com.pucpr.todolist.model;

import java.util.ArrayList;
import java.util.UUID;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){
        tasks.add(new Task("Passsar no mercado comprar comida", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("Levar o totó para passear", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("Comprar pneu novo para roda bike", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 0));
        tasks.add(new Task("lavar", UUID.randomUUID().clockSequence(), 1));
    };

    public static DataModel getInstance(){
        return instance;
    }

    public ArrayList<Task> tasks = new ArrayList<>();
}
