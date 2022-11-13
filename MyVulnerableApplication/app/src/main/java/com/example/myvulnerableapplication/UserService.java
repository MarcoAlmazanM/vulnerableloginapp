package com.example.myvulnerableapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Interface Name : UserService
    Purpose: The purpose of this interface is to organize all the different WebServices that the APIClient has, in order to force that
    all the classes use the same methods (names and parameters). This interface will help the API Client to find and handle the different
    WebServices that APIClient has.
*/

public interface UserService {
    //POST request to login a user
    @POST("login.php")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    // POST request to register new user
    @POST("register.php")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
