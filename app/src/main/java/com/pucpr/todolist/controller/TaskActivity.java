package com.pucpr.todolist.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pucpr.todolist.R;
import com.pucpr.todolist.model.DataModel;
import com.pucpr.todolist.model.Task;

public class TaskActivity extends AppCompatActivity {

    TextView taskViewTitle;
    EditText newTaskEditText;
    Button newTaskAddButton;
    boolean isToEditTask;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        newTaskEditText = findViewById(R.id.taskEditText);
        newTaskAddButton = findViewById(R.id.taskAddOrSaveButton);
        taskViewTitle = findViewById(R.id.taskViewTitle);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null && intentExtras.getBoolean("isToEditTask")) {
            isToEditTask = intentExtras.getBoolean("isToEditTask");
            position = intentExtras.getInt("position");


            taskViewTitle.setText(getString(R.string.edit_task_title));
            newTaskAddButton.setText(getString(R.string.btn_edit_Item));
            newTaskEditText.setText(DataModel.getInstance().tasks.get(position).getName());
        } else {
            newTaskEditText.setText("");
        }

    }

    public void addOrEditTaskActivityButtonOnClick(View v){
        String task = newTaskEditText.getText().toString();

        if(task.length() > 0 && isToEditTask) {
            Task oldTask = DataModel.getInstance().tasks.get(position);
            oldTask.setName(task.toString());
            finish();
        } else if (task.length() > 0) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newTaskName", task);
            setResult(Activity.RESULT_OK, resultIntent);

            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(getString(R.string.not_empty_task));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newTaskEditText.requestFocus();
                }
            });
            builder.create().show();
        }
    }
}