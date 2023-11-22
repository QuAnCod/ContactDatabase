package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private ContactManager contactManager;
    private List<Contact> contactsList;
    private ArrayAdapter<Contact> adapter; // You'll create a custom adapter later
    private FloatingActionButton fabAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ListView and the FloatingActionButton
        listViewContacts = findViewById(R.id.listViewContacts);
        fabAddContact = findViewById(R.id.fabAddContact);

        // Initialize the ContactManager
        contactManager = new ContactManager(this);

        // Retrieve contacts from the database
        contactsList = contactManager.getAllContacts(); // You'll implement this method in ContactManager later

        // Set up an ArrayAdapter (you will replace this with a custom adapter later)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        listViewContacts.setAdapter(adapter);

        // Set an OnClickListener on the FloatingActionButton
        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start an Activity to add a new contact
                Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
                startActivity(intent);
            }
        });

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact selectedContact = contactsList.get(position);

                Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
                intent.putExtra("CONTACT_ID", selectedContact.getId());
                startActivity(intent);
            }
        });

    }

    // Methods onResume to refresh the list
    @Override
    protected void onResume() {
        super.onResume();
        contactsList.clear();
        contactsList.addAll(contactManager.getAllContacts()); // Implement getAllContacts in ContactManager
        adapter.notifyDataSetChanged();
    }

}
