package com.example.myvulnerableapplication;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : LoginRequest
Purpose: The purpose of this class is to handle the request from the App to the API in order to verify the credential from a user. This
         class DO NOT implements Serializable to serialize the objects in order to convert into JSON, because the Library (Retrofit)
         that is been using to make the request to the API offers the ability to pass objects within the request body, the only request
         is to create the attributes with their corresponding getters and setters.
 */
public class LoginRequest {
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
