package com.example.retrofitapp.service;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://open-weather13.p.rapidapi.com/";

    // Singleton pattern used to create an instance of retrofit
    @NonNull
    public static GetWeatherDataService getService() {
        // when retrofit instance is not created
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //if retrofit instance is already created
        return retrofit.create(GetWeatherDataService.class);
    }
}
