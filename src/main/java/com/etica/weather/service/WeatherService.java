package com.etica.weather.service;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.Weather;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private static final Logger LOGGER = LogManager.getLogger(WeatherService.class);
    public static final int LIMIT_DEFAULT = 1;

    @Autowired
    private GeocodingClient geocodingClient;

    @Autowired
    private WeatherClient weatherClient;

    public Weather getWeather(String location, String unit, String apiKey) {
        Weather weather = null;
        try {
            weather = weatherClient.getWeather(location, unit, apiKey);
        } catch (FeignException ex) {
            LOGGER.error(String.format("Error occurred on OpenWeatherMap weather API call. Reason[%s]", ex.getMessage()));
        }
        return weather;
    }


    public Coordinates getCoordinates(String location, String apiKey) {
        return geocodingClient.getCoordinates(location, apiKey, LIMIT_DEFAULT).get(0);
    }
}
