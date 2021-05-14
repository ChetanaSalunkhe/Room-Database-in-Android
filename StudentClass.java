package com.example.mytodoroomdb.Student;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class StudentClass implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int studentId;

    @ColumnInfo (name = "studentName")
    private String studentName;

    @ColumnInfo (name = "marks")
    private String studentMaks;

    @ColumnInfo (name = "mobile")
    private String mobile;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMaks() {
        return studentMaks;
    }

    public void setStudentMaks(String studentMaks) {
        this.studentMaks = studentMaks;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
