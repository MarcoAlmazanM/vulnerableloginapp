package com.example.myvulnerableapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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