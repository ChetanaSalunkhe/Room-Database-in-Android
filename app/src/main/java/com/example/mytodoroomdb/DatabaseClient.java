package com.example.mytodoroomdb;

import android.content.Context;
import androidx.core.util.Pools;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class DatabaseClient {
    private Context mcxt;
    private static DatabaseClient mInstance;
    private AppDatabse appDatabse;

    private DatabaseClient(Context context){
        this.mcxt=context;

        //create appdatabase with roomdatabase builder
        appDatabse = Room.databaseBuilder(mcxt,AppDatabse.class,"MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mcxt){

        if(mInstance == null){
            mInstance = new DatabaseClient(mcxt);
        }
        return mInstance;
    }


    public AppDatabse getAppDatabase(){
        return appDatabse;
    }
}
