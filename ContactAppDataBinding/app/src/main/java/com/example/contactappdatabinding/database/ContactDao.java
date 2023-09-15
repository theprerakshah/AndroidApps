package com.example.contactappdatabinding.database;

import androidx.room.*;
import com.example.contactappdatabinding.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("Select * From contact_table")
    List<Contact> getAllContact();


}
