package com.example.contactdatabase;

public class Contact {
    private int id;
    private String name;
    private String dateOfBirth;
    private String email;
    private String avatarResourceName;

    // Constructor
    public Contact(int id, String name, String dateOfBirth, String email, String avatarResourceName) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.avatarResourceName = avatarResourceName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarResourceName() {
        return avatarResourceName;
    }

    public void setAvatarResourceName(String avatarResourceName) {
        this.avatarResourceName = avatarResourceName;
    }
}
