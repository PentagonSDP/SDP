package com.example.hosneara.project1;

/**
 * Created by hosneara on 10/19/16.
 */

public class user {
    private String Name, Email, Phone;
    user(String name, String email, String phone)
    {
        Name = name;
        Email = email;
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }
    public user()
    {
    }
}
