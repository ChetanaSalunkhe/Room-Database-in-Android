package com.example.mytodoroomdb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mytodoroomdb.DatabaseClient;
import com.example.mytodoroomdb.MainActivity;
import com.example.mytodoroomdb.R;
import com.example.mytodoroomdb.Task;

public class UpdateTaskActivity extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;
    private CheckBox checkBoxFinished;
    Button button_update,button_delete;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        init();

        task = (Task)getIntent().getSerializableExtra("task");

        //load task get data from database
        loadTask(task);

        setListeners();
    }

    public void init(){

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        checkBoxFinished = findViewById(R.id.checkBoxFinished);
        button_delete = findViewById(R.id.button_delete);
        button_update = findViewById(R.id.button_update);

    }

    public void setListeners(){
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask(task);
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(task);
            }
        });

    }

    public void loadTask(Task task){
        //get database values and set to views
        editTextDesc.setText(task.getDesc().toString());
        editTextTask.setText(task.getTask().toString());
        editTextFinishBy.setText(task.getFinishBy().toString());
        checkBoxFinished.setChecked(task.isFinished());

    }

    public void updateTask(final Task task){
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }

        class updateTask extends AsyncTask<Void,Void,Void>{

            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void avoid){
                super.onPostExecute(avoid);
                finish();
            }
        }

        updateTask ut = new updateTask();
        ut.execute();
    }

    private void deleteTask(final Task task){
        class DeleteTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }
}
