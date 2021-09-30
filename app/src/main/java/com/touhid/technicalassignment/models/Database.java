package com.touhid.technicalassignment.models;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {UserModel.class, ProductModel.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    //singleton
    private static Database instance;

    //database name
    private static String DATABASE_NAME = "order_automation_database";

    //abstract classes
    public abstract UserDao userDao();
    public abstract ProductDao productDao();


    public static synchronized Database getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
