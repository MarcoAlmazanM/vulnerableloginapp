package com.example.myvulnerableapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText eTNombre, eTAP,eTAM,
            eTEmailR, eTUsername, eTPassword, eTPasswordConfirm;
    String nombre, apellidoPaterno, apellidoMaterno, email, username, password, passwordConfirm, message;
    Integer codigo;
    TextInputLayout tNombre, tAP,tAM,
            tEmailR, tUsername, tPassword, tPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void verifyData(View view){
        // Find Id EditText
        eTNombre =findViewById(R.id.eTNombre);
        eTAP = findViewById(R.id.eTAP);
        eTAM = findViewById(R.id.eTAM);
        eTEmailR = findViewById(R.id.eTEmailR);
        eTUsername = findViewById(R.id.eTUsernameR);
        eTPassword = findViewById(R.id.eTPasswordR);
        eTPasswordConfirm = findViewById(R.id.eTPasswordConfirm);

        // Set strings UI
        nombre = eTNombre.getText().toString();
        apellidoPaterno = eTAP.getText().toString();
        apellidoMaterno = eTAM.getText().toString();
        email = eTEmailR.getText().toString();
        username = eTUsername.getText().toString();
        password = eTPassword.getText().toString();
        passwordConfirm = eTPasswordConfirm.getText().toString();

        if(TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellidoPaterno) || TextUtils.isEmpty(apellidoMaterno)  ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(passwordConfirm)){
            message = "Todos los campos son requeridos.";
            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
        }else if (!password.equals(passwordConfirm)){
            message = "Las contraseñas no coinciden .";
            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
        } else{
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setNombre(nombre);
            registerRequest.setApellidoP(apellidoPaterno);
            registerRequest.setApellidoM(apellidoMaterno);
            registerRequest.setCorreo(email);
            registerRequest.setIdUsuario(username);
            registerRequest.setContrasena(password);
            registerUser(registerRequest);
        }
    }

    public void userInfo(Integer codigo){
        tNombre = findViewById(R.id.textInputName);
        tAM = findViewById(R.id.textInputAM);
        tAP = findViewById(R.id.textInputAP);
        tUsername=findViewById(R.id.textInputUsername);
        tEmailR = findViewById(R.id.textInputEmail);
        tPassword = findViewById(R.id.textInputPassword);
        tPasswordConfirm = findViewById(R.id.textInputPasswordConf);
        tNombre.getEditText().setText(nombre);
        tAM.getEditText().setText(apellidoMaterno);
        tAP.getEditText().setText(apellidoPaterno);
        if(codigo == 0){
            tUsername.getEditText().setTextColor(Color.parseColor("#ff0000"));
            tUsername.getEditText().setText(username);
            tUsername.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    tUsername.getEditText().setTextColor(Color.BLACK);
                }
            });
            tEmailR.getEditText().setText(email);
        }else if(codigo == 1){
            tUsername.getEditText().setText(username);
            tEmailR.getEditText().setTextColor(Color.parseColor("#ff0000"));
            tEmailR.getEditText().setText(email);
            tEmailR.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    tEmailR.getEditText().setTextColor(Color.BLACK);
                }
            });
        }
        tPassword.getEditText().setText(password);
        tPasswordConfirm.getEditText().setText(password);
    }

    public void registerUser(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    try {
                        if (registerResponse.getRegisterApproval() == 1) {
                            message = "Usuario Registrado Exitosamente";
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                            Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                        } else {
                            setContentView(R.layout.activity_register);
                            message = registerResponse.getError();
                            if(message.equals("Usuario duplicado")){
                                codigo = 0;
                            }else if(message.equals("Correo duplicado")){
                                codigo=1;
                            }
                            userInfo(codigo);
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    }catch (NullPointerException nullPointerException){
                        message = "Error al registar, favor de intentar más tarde";
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    message ="Ocurrió un error, favor de intentar más tarde.";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goBackLogin(View view){
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();

    }

}