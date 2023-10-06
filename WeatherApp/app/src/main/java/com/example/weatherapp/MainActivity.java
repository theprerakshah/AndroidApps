package com.example.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.appmodle.Forecastday;
import com.example.weatherapp.appmodle.WeatherModel;
import com.example.weatherapp.recyclerview.MyAdapter;
import com.example.weatherapp.retrofit.RetrofitInstance;
import com.example.weatherapp.retrofit.RetrofitInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainRelativeLayout;
    private TextView currentCityName, currentTemp, weatherCondition, weatherFocastText;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageView weatherImage, searchIcon, backGroundImage;
    private TextInputEditText textInputEditText;
    private ArrayList<Forecastday> forecastdayArrayList;
    private MyAdapter myAdapter;

    private LocationManager locationManager;
    private int permissionCode = 1;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        currentCityName = findViewById(R.id.current_city);
        currentTemp = findViewById(R.id.temp_text);
        recyclerView = findViewById(R.id.recycle_view);
        weatherCondition = findViewById(R.id.weather_condition);
        weatherFocastText = findViewById(R.id.weather_forcast_text);
        weatherImage = findViewById(R.id.weather_image);
        searchIcon = findViewById(R.id.search_icon);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textInputEditText = findViewById(R.id.input_edit_text);
        backGroundImage = findViewById(R.id.idIV_background);
        mainRelativeLayout = findViewById(R.id.IDRL_relativeloayout);


        //to Get the users current location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // checking if the required permission is given by user. If not then it is asked again and again.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, permissionCode);
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        Location location = locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER);

        // form above location we only get longitude and latitude so it need to converted into city name

        while (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        cityName = getCityName(location.getLatitude(), location.getLongitude());
        currentCityName.setText(cityName);
        mainRelativeLayout.setVisibility(View.VISIBLE);
        getWeather(cityName);


        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = textInputEditText.getText().toString();
                if (newCity.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter the city name!!", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    currentCityName.setText(newCity);
                    getWeather(newCity);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == permissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission has been granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission has yet not granted ", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private String getCityName(double latitude, double longitude) {
        String cityname = "city not found!";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            ArrayList<Address> addresses = new ArrayList<>(gcd.getFromLocation(latitude, longitude, 2));
            for (Address address : addresses) {
                if (address != null) {
                    String city = address.getLocality();
                    if (city != null && !city.equals("")) {
                        cityname = city;
                    } else {
                        Log.i(TAG, "Unable to find city name based on longitude and latitude");
                        Toast.makeText(this, "Unable to find cityName based on longitude and latitude", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityname;
    }

    private void getWeather(String city) {
        RetrofitInterface retrofitInterface = RetrofitInstance.getService();
        Call<WeatherModel> call = retrofitInterface.getApiResponse("4ec51500582c4913bfb74256230210",
                city, 5, "no", "no");
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                WeatherModel weatherModel = response.body();
                if (weatherModel != null && weatherModel.getCurrent() != null) {
                    Log.i(TAG, "onResponse: weather in " + weatherModel.getLocation().getName()
                            + " city is " + weatherModel.getCurrent().getTempC());
                    currentCityName.setText(weatherModel.getLocation().getName());
                    currentTemp.setText(weatherModel.getCurrent().getTempC().toString() + "°C");
                    weatherCondition.setText(weatherModel.getForecast().getForecastday().get(0).getDay().getCondition().getText());
                    weatherFocastText = findViewById(R.id.weather_forcast_text);
                    Picasso.get().load("https://" + weatherModel.getCurrent().getCondition().getIcon()).into(weatherImage);
                    // night        https://unsplash.com/photos/TxoMYFip9d0
                    // day          https://unsplash.com/photos/KeQgKisq98I
                    if (weatherModel.getCurrent().getIsDay() == 1) {
                        Picasso.get().load("https://unsplash.com/photos/KeQgKisq98I").into(backGroundImage);
                    } else {
                        Picasso.get().load(" https://unsplash.com/photos/TxoMYFip9d0").into(backGroundImage);
                    }


                    textInputEditText.setText("");
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    forecastdayArrayList = new ArrayList<>(weatherModel.getForecast().getForecastday());
                    myAdapter = new MyAdapter(MainActivity.this, forecastdayArrayList);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                String code = t.getMessage();
                Log.i(TAG, "onFailure: not able to retrive data form api and response code:" + code);
            }
        });

    }
}