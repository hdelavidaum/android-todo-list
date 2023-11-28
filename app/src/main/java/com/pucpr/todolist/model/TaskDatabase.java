package com.pucpr.todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TaskDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "task.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Taks";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_IS_COMPLETED = "isCompleted";

    public TaskDatabase (Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " ("+
                COL_ID + "integer primary key autoincrement, " +
                COL_NAME + "text, " +
                COL_IS_COMPLETED + "integer" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public long createTaskInDB(@NonNull Task task){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, task.getName());
        values.put(COL_IS_COMPLETED, task.isCompleted());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public ArrayList<Task> getTasksFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);

        ArrayList<Task> tasks = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
              int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
              String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
              int isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IS_COMPLETED));

              tasks.add(new Task(name, id, isCompleted));
            } while (cursor.moveToNext());
        }
        database.close();
        return tasks;
    }

    public long updateTaskInDB(@NonNull Task task){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, task.getName());
        values.put(COL_IS_COMPLETED, task.isCompleted());
        String id = String.valueOf(task.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public long removeTaskInDB(@NonNull Task task){
        String id = String.valueOf(task.getId());
        SQLiteDatabase database = getWritableDatabase();
        long count = database.delete(DB_TABLE, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
}
