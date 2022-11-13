package com.example.myvulnerableapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : RegisterActivity
Purpose: The purpose of this class is to handle all the steps about the Register process, from the user enter the required information
(username, full name, password), making the request to the API and manage the response from the API about the successful register or
not.
 */
public class RegisterActivity extends AppCompatActivity {

    EditText eTNombre, eTAP,eTAM,
            eTEmailR, eTUsername, eTPassword, eTPasswordConfirm;
    String nombre, apellidoPaterno, apellidoMaterno, email, username, password, passwordConfirm, message, passwordHash;
    Integer codigo;
    TextInputLayout tNombre, tAP,tAM,
            tEmailR, tUsername, tPassword, tPasswordConfirm;
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onCreate
    Purpose: The purpose of this method is to set the View that is going to appear on the Screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : colorText
    Purpose: The purpose of this method is to handle the color of text on the inputs to represent if it is an error or not.
     */
    public void colorText(TextInputLayout myInputLayout, String myString) {

        myInputLayout.getEditText().setTextColor(Color.parseColor("#ff0000"));
        myInputLayout.getEditText().setText(myString);
        myInputLayout.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInputLayout.getEditText().setTextColor(Color.BLACK);
            }
        });

    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateName
    Purpose: The purpose of this method is to validate the Name of the new User using regex.
     */
    public boolean validateName() {
        String regex = "^[A-Za-z ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{1,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);

        boolean flag = matcher.matches();

        if (!flag) {
            tNombre = findViewById(R.id.textInputName);
            colorText(tNombre, nombre);
        }
        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateMiddleName
    Purpose: The purpose of this method is to validate the Middle Name of the new User using regex.
     */
    public boolean validateMiddleName() {
        String regex = "^[A-Za-z ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{1,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellidoPaterno);

        boolean flag = matcher.matches();

        if (!flag) {
            tAP = findViewById(R.id.textInputAP);
            colorText(tAP, apellidoPaterno);
        }
        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateLastName
    Purpose: The purpose of this method is to validate the Last Name of the new User using regex.
     */
    public boolean validateLastName() {
        String regex = "^[A-Za-z ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{1,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellidoMaterno);

        boolean flag = matcher.matches();

        if (!flag) {
            tAM = findViewById(R.id.textInputAM);
            colorText(tAM, apellidoMaterno);
        }
        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateEmail
    Purpose: The purpose of this method is to validate the Email of the new User using regex.
     */
    public boolean validateEmail() {
        boolean flag = true;

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException exception) {
            flag = false;
            tEmailR = findViewById(R.id.textInputEmail);
            colorText(tEmailR, email);
        }

        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateEmail
    Purpose: The purpose of this method is to validate the Username of the new User using regex.
     */
    public boolean validateUsername() {
        String regex = "^[0-9A-Za-z_\\- ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]{1,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        boolean flag = matcher.matches();

        if (!flag) {
            tUsername = findViewById(R.id.textInputUsername);
            colorText(tUsername, username);
        }
        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateEmail
    Purpose: The purpose of this method is to validate the Password of the new User using regex.
     */
    public boolean validatePassword() {
        String regex = "^(?=.*[a-zñ])(?=.*[A-ZÑ])(?=.*\\d)(?=.*[@$!%*?._&])[A-ZÑa-zñ\\d@$!%*?._&]{8,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        boolean flag = matcher.matches();

        if (!flag) {
            tPassword = findViewById(R.id.textInputPassword);
            colorText(tPassword, password);
        }
        return flag;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateEmail
    Purpose: The purpose of this method is to validate the Password Confirm of the new User using regex.
     */
    public boolean validatePasswordConfirm() {
        String regex = "^(?=.*[a-zñ])(?=.*[A-ZÑ])(?=.*\\d)(?=.*[@$!%*?._&])[A-ZÑa-zñ\\d@$!%*?._&]{8,255}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passwordConfirm);

        boolean flag = matcher.matches();

        if (!flag) {
            tPasswordConfirm = findViewById(R.id.textInputPasswordConf);
            colorText(tPasswordConfirm, passwordConfirm);
        }
        return flag;
    }

    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : validateFields
    Purpose: The purpose of this method is to check that all the inputs are correct based on regex, if all the inputs are correct
    the method returns true, if not returns false and the error message.
     */
    public boolean validateFields() {

        boolean name = false;
        boolean middle = false;
        boolean last = false;
        boolean email = false;
        boolean username = false;
        boolean pass = false;
        boolean confirmpass = false;

        TextView tname, tmiddle, tlast, temail, tusername, tpass, tconfirmpass;

        if (validateName()) {
            name = true;
            tname = findViewById(R.id.tVNameNotAccepted);
            tname.setVisibility(View.GONE);
        }
        else {
            tname = findViewById(R.id.tVNameNotAccepted);
            tname.setVisibility(View.VISIBLE);
            tname.setTextColor(Color.RED);
            String message = "El nombre no acepta caracteres especiales.";
            tname.setText(message);
        }
        if (validateMiddleName()) {
            middle = true;
            tmiddle = findViewById(R.id.tVAMNotAccepted);
            tmiddle.setVisibility(View.GONE);
        }
        else {
            tmiddle = findViewById(R.id.tVAMNotAccepted);
            tmiddle.setVisibility(View.VISIBLE);
            tmiddle.setTextColor(Color.RED);
            String message = "El apellido materno no acepta caracteres especiales.";
            tmiddle.setText(message);
        }
        if (validateLastName()) {
            last = true;
            tlast = findViewById(R.id.tVAPNotAccepted);
            tlast.setVisibility(View.GONE);
        }
        else {
            tlast = findViewById(R.id.tVAPNotAccepted);
            tlast.setVisibility(View.VISIBLE);
            tlast.setTextColor(Color.RED);
            String message = "El apellido paterno no acepta caracteres especiales.";
            tlast.setText(message);
        }
        if (validateEmail()) {
            email = true;
            temail = findViewById(R.id.tVEmailNotAccepted);
            temail.setVisibility(View.GONE);
        }
        else {
            temail = findViewById(R.id.tVEmailNotAccepted);
            temail.setVisibility(View.VISIBLE);
            temail.setTextColor(Color.RED);
            String message = "El correo electrónico no es válido.";
            temail.setText(message);

        }
        if (validateUsername()) {
            username = true;
            tusername = findViewById(R.id.tVUserNameNotAccepted);
            tusername.setVisibility(View.GONE);
        }
        else{
            tusername = findViewById(R.id.tVUserNameNotAccepted);
            tusername.setVisibility(View.VISIBLE);
            tusername.setTextColor(Color.RED);
            String message = "El usuario no acepta caracteres especiales.";
            tusername.setText(message);
        }
        if (validatePassword()) {
            pass = true;
            tpass = findViewById(R.id.tVPassNotAccepted);
            tpass.setVisibility(View.GONE);
        }
        else{
            tpass = findViewById(R.id.tVPassNotAccepted);
            tpass.setVisibility(View.VISIBLE);
            tpass.setTextColor(Color.RED);
            String message = "Mínimo ocho caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial.";
            tpass.setText(message);
        }
        if (validatePasswordConfirm()) {
            confirmpass = true;
            tconfirmpass = findViewById(R.id.tVPassConfNotAccepted);
            tconfirmpass.setVisibility(View.GONE);
        }
        else{
            tconfirmpass = findViewById(R.id.tVPassConfNotAccepted);
            tconfirmpass.setVisibility(View.VISIBLE);
            tconfirmpass.setTextColor(Color.RED);
            String message = "Mínimo ocho caracteres, al menos una letra mayúscula, una letra minúscula, un número y un caracter especial.";
            tconfirmpass.setText(message);
        }

        return (name && middle && last && email && username && pass && confirmpass);
    }


    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : verifyData
    Purpose: The purpose of this method is to check that all the inputs are filled out by the user, also checks that the password
    meets all the requirements to be a secure password, also checks that both password are the same. If all the validations are meeting
    the method call the registerUser method.
     */
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
        }
        else if (validateFields()) {
            Utils utils = new Utils();
            passwordHash = utils.hash256(password);
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setNombre(nombre);
            registerRequest.setApellidoP(apellidoPaterno);
            registerRequest.setApellidoM(apellidoMaterno);
            registerRequest.setCorreo(email);
            registerRequest.setIdUsuario(username);
            registerRequest.setContrasena(passwordHash);
            registerUser(registerRequest);

        }
        else {
                message = "Algún campo es incorrecto.";
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
        }
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : userInfo
    Purpose: The purpose of this method is to handle the possible errors retrieved by the API on the user register process.
    */
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

    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : registerUser
    Purpose: The purpose of this method is to handle the Register Intent, also this method handles the Request Response that can be
    that the user was register successfully, or possible errors with username and email.
    */
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
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : onBackPressed
    Purpose: The purpose of this method is to return to the Login Screen when a user press the back button on the app.
     */
    public void goBackLogin(View view){
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();

    }

}