package com.example.roomdb.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.roomdb.entity.Contact;
import com.example.roomdb.entity.ContactDAO;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    //Linking the DAO with database
    public abstract ContactDAO getContactDao();
}
