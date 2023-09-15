package com.example.contactappdatabinding;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.contactappdatabinding.database.ContactDao;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

}
