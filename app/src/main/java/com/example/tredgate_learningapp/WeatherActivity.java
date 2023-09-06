package com.example.tredgate_learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {
    private TextView weatherLocation;
    private TextView weatherTemp;
    private OkHttpClient client;
    private TextView weatherDescription;
    private TextView weatherHumidity;
    private TextView weatherWindSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Button backButton = findViewById(R.id.backButton);
        Button refreshButton = findViewById(R.id.refreshButton);
        weatherLocation = findViewById(R.id.weatherLocation);
        weatherTemp = findViewById(R.id.weatherTemp);
        weatherDescription = findViewById(R.id.weatherDescription);
        weatherHumidity = findViewById(R.id.weatherHumidity);
        weatherWindSpeed = findViewById(R.id.weatherWindSpeed);

        client = new OkHttpClient();

        backButton.setOnClickListener(view -> finish());

        refreshButton.setOnClickListener(view -> loadWeatherData());

        loadWeatherData();
    }

    private void loadWeatherData() {
        String url = "https://openweathermap.org/data/2.5/weather?lat=50.088&lon=14.4208&units=metric&lang=en&appid=439d4b804bc8187953eb36d2a8c26a02&units=metric";
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(myResponse);

                        final String location = jsonResponse.getString("name");
                        final JSONObject mainObj = jsonResponse.getJSONObject("main");
                        final double temp = mainObj.getDouble("temp");
                        JSONArray weatherArray = jsonResponse.getJSONArray("weather");
                        JSONObject weatherObj = weatherArray.getJSONObject(0);
                        final String description = weatherObj.getString("description");

                        final int humidity = mainObj.getInt("humidity");

                        final JSONObject windObj = jsonResponse.getJSONObject("wind");
                        final double windSpeed = windObj.getDouble("speed");

                        runOnUiThread(() -> {
                            weatherLocation.setText(String.format("Město: %s", location));
                            weatherTemp.setText(String.format("Teplota: %.2f°C", temp));
                            weatherDescription.setText(String.format("Popis: %s", description));
                            weatherHumidity.setText(String.format("Vlhkost: %d%%", humidity));
                            weatherWindSpeed.setText(String.format("Rychlost Větru: %.2f m/s", windSpeed));
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}


//API KEY: d3e9139ff925a1d4a8850e4e2a9bf505
//String url = "";