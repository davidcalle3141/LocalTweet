package com.gmail.davidcalle3141.ny.ttp_me.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Group.class},version = 1,exportSchema = true)
public abstract class Database extends RoomDatabase {
    private static final String LOG_TAG = Database.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "GroupList";
    private static Database sInstance;

    public static Database getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "creating new DB instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, Database.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }Log.d(LOG_TAG, "getting DB instance");
        return sInstance;
    }

    public abstract GroupDao groupDao();

}
