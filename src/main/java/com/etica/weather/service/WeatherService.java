package com.etica.weather.service;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private static final int LIMIT_DEFAULT = 1;

    @Autowired
    private GeocodingClient geocodingClient;

    @Autowired
    private WeatherClient weatherClient;

    public Weather getWeather(String location, String unit, String apiKey) {
        return weatherClient.getWeather(location, unit, apiKey);
    }


    public Coordinates getCoordinates(String location, String apiKey) {
        return geocodingClient.getCoordinates(location, apiKey, LIMIT_DEFAULT).get(0);
    }
}
