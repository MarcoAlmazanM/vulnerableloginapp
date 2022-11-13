package com.example.myvulnerableapplication;

/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : RegisterRequest
Purpose: The purpose of this class is to handle the request from the App to the API in order to register a new user. This class DO NOT
         implements Serializable to serialize the objects in order to convert into JSON, because the Library (Retrofit) that is been
         using to make the request to the API offers the ability to pass objects within the request body, the only request is to create
         the attributes with their corresponding getters and setters.
 */

public class RegisterRequest {
    private String idUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String contrasena;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

