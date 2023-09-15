package com.example.lma.entity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();

    public abstract CourseDao courseDao();

    //For Singleton pattern
    private static CourseDatabase instance;

    public static synchronized CourseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabase.class, "course_database"
            ).fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }

        return instance;
    }

    //callBack
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //insert data when db is created

        }
    };

}
