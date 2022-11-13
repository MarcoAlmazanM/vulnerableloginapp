package com.example.myvulnerableapplication;

import java.io.Serializable;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : RegisterResponse
Purpose: The purpose of this class is to handle the response from the API that returns if a User has been register or not. This class
         implements Serializable to serialize the objects in order to convert into JSON. Also this class has the attributes with
         the getters and setters to handle the JSON response.
 */
public class RegisterResponse implements Serializable {
    private Integer registerApproval;
    private String error;


    public Integer getRegisterApproval() {
        return registerApproval;
    }

    public void setRegisterApproval(Integer registerApproval) {
        this.registerApproval = registerApproval;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}