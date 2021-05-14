package com.example.mytodoroomdb.Student;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface StudentDao {

    @Query("Select * from studentclass")
    List<StudentClass> getAllStudents();

    @Insert
    void insert(StudentClass stClass);

    @Delete
    void delete(StudentClass stCLass);

    @Update
    void update(StudentClass studentClass);
}
