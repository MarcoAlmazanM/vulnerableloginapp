package com.example.myvulnerableapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : MainActivity
Purpose: The purpose of this class is to set the Main Activity after a user logged in the app.
 */
public class MainActivity extends AppCompatActivity {
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onCreate
    Purpose: The purpose of this method is to start the Main Activity setting the information related to the user logged.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        SharedPreferences preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String name = preferences.getString("name","Nombre del Usuario");
        String lastName = preferences.getString("lastName","");
        tvUsername.setText("¡Bienvenido + " + name +  "\n" + lastName + "!");
    }
}