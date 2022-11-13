package com.example.myvulnerableapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
Class Name : ApiClient
Purpose: The purpose of this class is to set all the configurations to be able to connect with the APIClient.
 */
public class ApiClient {
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : getRetrofit
    Purpose: The purpose of this method is to start the connection with the APIClient to be able to handle the different requests &
    responses generated.
    */
    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://servermyvulnerableapp.azurewebsites.net/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    /*
    Author: Marco Antonio Almazan Martinez & Mariana Soto Ochoa
    Method Name : getRetrofit
    Purpose: The purpose of this method is to use the interface generated to handle the different WebServices that the APIClient has.
    */
    public static UserService getService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }
}
