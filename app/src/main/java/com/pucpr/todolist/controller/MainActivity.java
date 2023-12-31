package com.pucpr.todolist.controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pucpr.todolist.R;
import com.pucpr.todolist.model.DataModel;
import com.pucpr.todolist.model.Task;
import com.pucpr.todolist.model.TaskAdapter;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewItemsList;
    TaskAdapter taskAdapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewItemsList = findViewById(R.id.recyclerViewItemsList);
        DataModel.getInstance().createDataBase(getApplicationContext());

        recyclerViewItemsList.setAdapter(taskAdapter);
        recyclerViewItemsList.setLayoutManager(
                new LinearLayoutManager(MainActivity.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                MainActivity.this,
                DividerItemDecoration.VERTICAL
        );
        recyclerViewItemsList.addItemDecoration(itemDecoration);

        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToTaskDetailsOnClick(position);
            }
        });

        taskAdapter.setItemRemoveButtonOnClickListener(new TaskAdapter.ItemRemoveButtonOnClickListener() {
            @Override
            public boolean onRemoveItemClick(View view, int position) {
                DataModel.getInstance().deleteTask(position);
                taskAdapter.notifyItemRemoved(position);

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskAdapter.notifyDataSetChanged();
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK
                        && result.getData() != null){
                        Intent data = result.getData();
                        String newTaskName = data.getStringExtra("newTaskName");
                        Log.d("newTaskName", newTaskName);

                        DataModel.getInstance().addTask(new Task(newTaskName, 0));
                    }
                }
            }
    );

    public void goToAddTaskActivityButtonOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        activityResultLauncher.launch(intent);
    }

    public void goToTaskDetailsOnClick(int index){
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("position", index);
        intent.putExtra("isToEditTask", true);
        activityResultLauncher.launch(intent);
    }
}