package com.example.myvulnerableapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : SplashScreen
Purpose: The purpose of this class is to show the logo of the app when the user first opens it and after that redirect the user
         to the Login Screen.
 */
public class SplashScreen extends AppCompatActivity {
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onCreate
    Purpose: The purpose of this method is to start the Activity to redirect the user to the Login Screen.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        finish();

    }
}
