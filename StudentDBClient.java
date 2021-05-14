package com.example.mytodoroomdb.Student;

import android.content.Context;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class StudentDBClient {
    private Context mContext;
    private static StudentDBClient studentInstance;
    private StudentDatabase studentDatabase;

    public StudentDBClient(Context context){
        this.mContext = context;

        final Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE task "
                        + " ADD COLUMN studentId TEXT");
            }
        };

        studentDatabase = Room.databaseBuilder(mContext,StudentDatabase.class,"Student")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized StudentDBClient getDbInstance(Context context){

        if(studentInstance == null){
            studentInstance = new StudentDBClient(context);
        }

        return studentInstance;
    }

    public StudentDatabase getStudentDatabase(){
        return studentDatabase;
    }

}
