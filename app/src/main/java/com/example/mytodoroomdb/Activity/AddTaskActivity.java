package com.example.mytodoroomdb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytodoroomdb.DatabaseClient;
import com.example.mytodoroomdb.MainActivity;
import com.example.mytodoroomdb.R;
import com.example.mytodoroomdb.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTask, editTextDesc, editTextFinishBy;
    Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        init();

        setListeners();
    }activity_add_task

    public void init(){
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        button_save = findViewById(R.id.button_save);
    }

    public void setListeners(){
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask(){

        final String sTask = editTextTask.getText().toString();
        final String sDesc = editTextDesc.getText().toString();
        final String sFinishBy = editTextFinishBy.getText().toString();

        if(sTask.isEmpty()){
            editTextTask.setError("Task Required");
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

        class SaveTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }

        }

        SaveTask st = new SaveTask();
        st.execute();

    }

}
