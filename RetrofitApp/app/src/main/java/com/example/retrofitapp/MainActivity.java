package com.example.retrofitapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofitapp.model.MyModle;
import com.example.retrofitapp.service.GetWeatherDataService;
import com.example.retrofitapp.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    MyModle myModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getWeather();
    }

    public MyModle getWeather() {

        GetWeatherDataService getWeatherDataService = RetrofitInstance.getService();
        Call<MyModle> call = getWeatherDataService.getMyModle("ahmedabad");
        call.enqueue(new Callback<MyModle>() {
            @Override
            public void onResponse(Call<MyModle> call, Response<MyModle> response) {
                MyModle myModle = response.body();
                if (myModle != null && myModle.getMain() != null) {

//                    Double tempInCelcius = ((myModle.getMain().getTemp() - 32) * (5 / 9));

                    Log.i(TAG, "onResponse: weather in " + myModle.getName() + "is " + myModle.getMain().getTemp());
                }
            }

            @Override
            public void onFailure(Call<MyModle> call, Throwable t) {
                Log.i(TAG, "Not able to retirve data form api");
            }
        });

        return myModle;
    }

}