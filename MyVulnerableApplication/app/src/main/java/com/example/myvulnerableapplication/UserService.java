package com.example.myvulnerableapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    //POST request to login a user
    @POST("login.php")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    // POST request to register new user
    @POST("register.php")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
