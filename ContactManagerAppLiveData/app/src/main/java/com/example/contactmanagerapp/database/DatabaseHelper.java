package com.example.contactmanagerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.contactmanagerapp.database.entity.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contac_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // CREATE A TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contact.CREATE_TABLE);
    }

    // DROP A TABLE IF EXISTS AND CREATE A NEW TABLE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        if(db.rawQuery("IF (SELECT COUNT(*) FROM contac_db.tables WHERE table_schema =  DATABASE() AND table_name = contacts)= 1  THEN   RETURN TRUE;    ELSE RETURN FALSE; ENDIF;",null);


        db.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_NAME);
        onCreate(db);
    }

    // INSERT DATA INTO TABLES

    public long insertIntoTable(String name, String email) {
        SQLiteDatabase SqliteDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.COLUMN_NAME, name);
        contentValues.put(Contact.COLUMN_EMAIL, email);
        long id = SqliteDb.insert(Contact.TABLE_NAME, null, contentValues);
        SqliteDb.close();
        return id;
    }
    // Retriving data form database

    public Contact getContact(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Contact.TABLE_NAME,
                new String[]{
                        Contact.COLUMN_ID, Contact.COLUMN_NAME, Contact.COLUMN_EMAIL
                },
                Contact.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null, null, null, null
        );

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)),
                cursor.getInt((cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)))
        );
        cursor.close();


        return contact;
    }


    // Get All contact form db
    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> myList = new ArrayList<>();

        String AllSelectQuery = "SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY " + Contact.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(AllSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));

            } while (cursor.moveToNext());
        }
        db.close();
        return myList;
    }

    // Update the particular row in the table
    public int updateRowTable(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Contact.COLUMN_NAME, contact.getName());
        content.put(Contact.COLUMN_EMAIL, contact.getName());
        return db.update(Contact.TABLE_NAME,
                content,
                Contact.COLUMN_ID + " =? ",
                new String[]{String.valueOf(contact.getId())});
    }

    public int deleteRowTable(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " =? ", new String[]{String.valueOf(contact.getId())});
        db.close();
        return i;
    }


}
