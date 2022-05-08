package com.llopez.ejercicio2.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicio {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.143.169.80:5000/sql/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static APIService service =
            retrofit.create(APIService.class);
}