package com.example.myvulnerableapplication;

import java.io.Serializable;

/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : LoginResponse
Purpose: The purpose of this class is to handle the response from the API that returns if a User has been log in or not. This class
         implements Serializable to serialize the objects in order to convert into JSON. Also this class has the attributes with
         the getters and setters to handle the JSON response.
 */

public class LoginResponse implements Serializable {
    private String name;
    private String lastName;
    private String lastNameM;
    private String email;
    private String username;
    private Integer loginApproval;

    public Integer getLoginApproval() {
        return loginApproval;
    }

    public void setLoginApproval(Integer loginApproval) {
        this.loginApproval = loginApproval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }


}
