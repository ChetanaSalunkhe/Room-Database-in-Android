package com.example.mytodoroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.mytodoroomdb.Activity.AddTaskActivity;
import com.example.mytodoroomdb.Activity.TasksAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context parent;
    RecyclerView recyclerview_tasks;
    FloatingActionButton btnaddtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setListeners();

        getTasks();

    }

    public void init(){
        parent = MainActivity.this;

        recyclerview_tasks = findViewById(R.id.recyclerview_tasks);
        btnaddtask = findViewById(R.id.btnaddtask);

        recyclerview_tasks.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setListeners(){
        btnaddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getTasks(){
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {

                List<Task> taskList = DatabaseClient.getInstance(parent).getAppDatabase().taskDao().getAll();

                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);

                TasksAdapter tasksAdapter = new TasksAdapter(MainActivity.this,tasks);
                recyclerview_tasks.setAdapter(tasksAdapter);

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTasks();
    }
}
