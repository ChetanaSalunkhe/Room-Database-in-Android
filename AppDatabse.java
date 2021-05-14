package com.example.mytodoroomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabse extends RoomDatabase {

    public abstract TaskDao taskDao();
}
