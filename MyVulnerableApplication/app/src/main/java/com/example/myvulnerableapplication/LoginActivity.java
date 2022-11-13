package com.example.myvulnerableapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : LoginActivity
Purpose: The purpose of this class is to handle all the steps about the Log in process, from the user enter the required information
(username, password), making the request to the API, handle the response from the API and finally authorizing or not the access to the
app.
 */
public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String message;

    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onCreate
    Purpose: The purpose of this method is to set the View that is going to appear on the Screen, and also is going to call the
    loginIntent method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For changing status bar icon color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);
        preferences = this.getSharedPreferences("userData",Context.MODE_PRIVATE);
        editor = preferences.edit();

        loginIntent();
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : btnLogin
    Purpose: This method is called when the user click on the Sign In Button and the purpose is to verify that all the inputs are filled
    out, if all the inputs are filled out the method create a LoginRequest and call the method loginUser to continue with the process.
     */
    public void btnLogin(View view){
        edUsername = findViewById(R.id.eTUsername);
        edPassword = findViewById(R.id.eTPassword);
        if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
            message = "Todos los campos son requeridos.";
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
        } else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(edUsername.getText().toString());
            Utils utils = new Utils();
            String password = utils.hash256(edPassword.getText().toString());
            loginRequest.setPassword(password);
            loginUser(loginRequest);
        }
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : btnRegister
    Purpose: This method is called when the user click on the New Account Button and the purpose is to redirect the user to the Register
    Screen.
     */
    public void btnRegister(View view){
        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register);
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : loginIntent
    Purpose: The purpose of this method is to handle the Login Intent, also this method handles the Login Response to authorize the
    access to the app.
     */
    public void loginIntent(){
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            LoginResponse loginResponse = (LoginResponse) intent.getSerializableExtra("data");
            try {
                if (loginResponse.getLoginApproval() == 1) {
                    preferences = this.getSharedPreferences("userData", Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    editor.putString("name", loginResponse.getName());
                    editor.putString("lastName", loginResponse.getLastName());
                    editor.putString("lastNameM", loginResponse.getLastNameM());
                    editor.putString("username", loginResponse.getUsername());
                    editor.putString("email", loginResponse.getEmail());
                    editor.apply();
                    Intent menu = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(menu);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    finish();
                } else if(loginResponse.getLoginApproval() == 0) {
                    message = "Usuario o Contraseña Incorrectos";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }else{
                    message = "Usuario o Contraseña Incorrectos.";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }catch(NullPointerException nullPointerException){
                message = "Inicio Fallido, favor de intentar más tarde";
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : loginUser
    Purpose: The purpose of this method is to make the request to the APIClient to verify if a User is able to enter to the app.
    This method handles the response of the API call and set the data to verify the Log In.
     */
    public void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class).putExtra("data", loginResponse));
                    finish();
                } else {
                    message = "Ocurrió un error, favor de intentar más tarde";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onBackPressed
    Purpose: The purpose of this method is to close the app when a user press the back button on the app.
     */
    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}