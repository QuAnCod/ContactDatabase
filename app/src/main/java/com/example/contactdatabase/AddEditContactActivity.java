package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEditContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextDateOfBirth, editTextEmail;
    private Spinner spinnerAvatar;
    private Button buttonSave;
    private ContactManager contactManager;
    private int contactId = -1; // Default -1, it means a new contact

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        // Initializing views
        editTextName = findViewById(R.id.editTextName);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerAvatar = findViewById(R.id.spinnerAvatar);
        buttonSave = findViewById(R.id.buttonSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.avatar_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAvatar.setAdapter(adapter);

        // Initialize ContactManager
        contactManager = new ContactManager(this);

        // Check if we are in edit mode (you can pass data via Intent)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            contactId = extras.getInt("CONTACT_ID", -1);
            if (contactId != -1) {
                // Load contact data and populate fields
                Contact contact = contactManager.getContact(contactId);
                editTextName.setText(contact.getName());
                editTextDateOfBirth.setText(contact.getDateOfBirth());
                editTextEmail.setText(contact.getEmail());
                spinnerAvatar.setSelection(getAvatarResourceId(contact.getAvatarResourceName()));
            }
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        String name = editTextName.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String email = editTextEmail.getText().toString();
        String selectedAvatar = spinnerAvatar.getSelectedItem().toString();

        Contact contact = new Contact(contactId, name, dateOfBirth, email, selectedAvatar);

        if (contactId == -1) {
            // Add new contact
            contactManager.addContact(contact);
        } else {
            // Update existing contact
            contactManager.updateContact(contact);
        }

        finish(); // Close the activity and return to the previous one
    }

    // Set up spinner for avatar selection
    private int getAvatarResourceId(String avatarResourceName) {
        return getResources().getIdentifier(avatarResourceName, "drawable", getPackageName());
    }
}