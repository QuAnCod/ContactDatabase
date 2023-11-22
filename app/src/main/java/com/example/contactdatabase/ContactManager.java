package com.example.contactdatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private DatabaseHelper dbHelper;

    public ContactManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Method to add a new contact
    public long addContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("date_of_birth", contact.getDateOfBirth());
        values.put("email", contact.getEmail());
        values.put("avatar_resource_name", contact.getAvatarResourceName());

        long id = db.insert("contacts", null, values);
        db.close();
        return id;
    }

    // Method to fetch all contacts from the database
    public List<Contact> getAllContacts() {
        List<Contact> contactsList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM contacts";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                contactsList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsList;
    }

    // Method to update a contact
    public void updateContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("date_of_birth", contact.getDateOfBirth());
        values.put("email", contact.getEmail());
        values.put("avatar_resource_name", contact.getAvatarResourceName());

        db.update("contacts", values, "id = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    // Method to get a contact
    @SuppressLint("Range")
    public Contact getContact(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("contacts", new String[]{"id", "name", "date_of_birth", "email", "avatar_resource_name"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("date_of_birth")),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("avatar_resource_name"))
        );

        cursor.close();
        return contact;
    }
}
