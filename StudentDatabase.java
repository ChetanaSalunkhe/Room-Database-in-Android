package com.example.mytodoroomdb.Student;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.mytodoroomdb.Task;
import com.example.mytodoroomdb.TaskDao;

@Database(entities = {StudentClass.class, Task.class},version = 2)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract TaskDao taskDao();
    
}
