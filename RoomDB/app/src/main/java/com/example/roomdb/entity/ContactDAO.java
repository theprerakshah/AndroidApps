package com.example.roomdb.entity;

import androidx.room.*;

import java.util.List;
@Dao
public interface ContactDAO {
    @Insert
    public long addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);


    @Query("Select * from contacts")
    public List<Contact> getAllContact();

    @Query("Select * from contacts where contact_id==:contactID")
    public Contact getContact(long contactID);
}
