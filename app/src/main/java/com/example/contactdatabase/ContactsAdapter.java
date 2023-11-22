package com.example.contactdatabase;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        super();
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position); // You'll implement this method in the next task
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId(); // You'll implement this method in the next task
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        }

        // Get the data item for this position
        Contact contact = (Contact) getItem(position);

        // Lookup view for data population
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDateOfBirth = convertView.findViewById(R.id.textViewDateOfBirth);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);

        // Populate the data into the template view using the data object
        textViewName.setText(contact.getName());
        textViewDateOfBirth.setText(contact.getDateOfBirth());
        textViewEmail.setText(contact.getEmail());

        // For the avatar, you'll need to set the resource based on the stored name
        int resId = context.getResources().getIdentifier(contact.getAvatarResourceName(), "drawable", context.getPackageName());
        imageViewAvatar.setImageResource(resId);

        // Return the completed view to render on screen
        return convertView;
    }
}
