package com.example.hosneara.project1;

/**
 * Created by hosneara on 10/19/16.
 */

public class user {
    private static String name, email, phone;
    private static user instance = new user();
    private user() {}
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail() {return this.email;}

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public static user getInstance()
    {
        return instance;
    }

}
