package com.example.mytodoroomdb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mytodoroomdb.R;
import com.example.mytodoroomdb.Student.StudentClass;
import com.example.mytodoroomdb.Student.StudentDBClient;
import com.example.mytodoroomdb.Student.StudentRecycleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    Context context;
    LinearLayout layoutadd;
    Button btnsave;
    EditText edtmarks,edtname,edtmob;
    FloatingActionButton btnaddStudent;
    RecyclerView list_students;

    StudentRecycleAdapter stAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        init();

        getStudents();

        setListeners();

    }

    public void init(){
        context = StudentListActivity.this;

        list_students = findViewById(R.id.list_students);
        btnaddStudent = findViewById(R.id.btnaddStudent);
        edtmob = findViewById(R.id.edtmob);
        edtname = findViewById(R.id.edtname);
        edtmarks = findViewById(R.id.edtmarks);
        layoutadd = findViewById(R.id.layoutadd);
        layoutadd.setVisibility(View.GONE);
        btnsave = findViewById(R.id.btnsave);

        list_students.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setListeners(){

        btnaddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutadd.setVisibility(View.VISIBLE);

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save in database
                addStudent();
            }
        });

    }

    public void getStudents(){

        class GetStudents extends AsyncTask<Void,Void, List<StudentClass>> {

            @Override
            protected List<StudentClass> doInBackground(Void... voids) {

                List<StudentClass> classList = StudentDBClient.getDbInstance(context).getStudentDatabase().studentDao().getAllStudents();

                return classList;
            }

            @Override
            protected void onPostExecute(List<StudentClass> stClass) {
                super.onPostExecute(stClass);

                stAdapter = new StudentRecycleAdapter(context,stClass);
                list_students.setAdapter(stAdapter);
            }
        }

        GetStudents gtStudent = new GetStudents();
        gtStudent.execute();

    }

    public void addStudent(){

        final String stName = edtname.getText().toString();
        final String stMob = edtmob.getText().toString();
        final String stMarks = edtmarks.getText().toString();


        class AddStudent extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                StudentClass stCLass = new StudentClass();
                stCLass.setStudentName(stName);
                stCLass.setMobile(stMob);
                stCLass.setStudentMaks(stMarks);

                StudentDBClient.getDbInstance(context).getStudentDatabase().studentDao().insert(stCLass);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(context,"Student added to list",Toast.LENGTH_SHORT).show();
                layoutadd.setVisibility(View.GONE);
                getStudents();
            }
        }

        AddStudent adStudent = new AddStudent();
        adStudent.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudents();
    }
}
