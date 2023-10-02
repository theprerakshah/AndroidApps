package com.example.retrofitapp.service;

import com.example.retrofitapp.model.MyModle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetWeatherDataService {

    //https://open-weather13.p.rapidapi.com/city/ahmedabad

    @Headers({"X-RapidAPI-Key: 5562fac321mshe2f49aff2685edfp1e6fafjsn008350e3e261", "X-RapidAPI-Host:open-weather13.p.rapidapi.com"})
    @GET("city/{city}")
    Call<MyModle> getMyModle(@Path("city") String city);

}
