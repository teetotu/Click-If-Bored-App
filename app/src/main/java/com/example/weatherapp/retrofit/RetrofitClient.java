package com.example.weatherapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    // https://picsum.photos/v2                 - image
    // https://api.openweathermap.org/data/2.5/ - weather
    public static Retrofit getInstance(String uri) {
        if (instance == null) {
            instance = new Retrofit.Builder().baseUrl(uri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        return instance;
    }
}
