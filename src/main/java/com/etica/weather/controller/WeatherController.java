package com.etica.weather.controller;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.Weather;
import com.etica.weather.service.GeocodingClient;
import com.etica.weather.service.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private GeocodingClient geocodingClient;

    @Autowired
    private WeatherClient weatherClient;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @GetMapping("/weather/{location}")
    public Weather getWeather(@PathVariable("location") String location) {
        Coordinates coordinates = geocodingClient.getCoordinates(location, apiKey, 1).get(0);
        Weather weather = weatherClient.getWeather(coordinates.getLat(), coordinates.getLon(), apiKey);
        return weather;
    }

    @GetMapping("/geolocation/{location}")
    public Coordinates getGeoLocation(@PathVariable("location") String location) {
        Coordinates coordinates = geocodingClient.getCoordinates(location, apiKey, 1).get(0);
        return coordinates;
    }
}
