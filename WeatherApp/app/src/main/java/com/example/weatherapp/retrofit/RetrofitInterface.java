package com.example.weatherapp.retrofit;

import com.example.weatherapp.appmodle.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    //    @GET("key/{key}/q/{q}/days{days}/aqi{aqi}/alerts{alerts}")
    @GET("v1/forecast.json")
    Call<WeatherModel> getApiResponse(@Query("key") String key, @Query("q") String city,
                                      @Query("days") int days, @Query("aqi") String aqi, @Query("alerts") String alerts);
}
