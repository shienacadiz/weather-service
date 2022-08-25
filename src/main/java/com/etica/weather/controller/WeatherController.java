package com.etica.weather.controller;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.TempUnitsEnum;
import com.etica.weather.model.Weather;
import com.etica.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @GetMapping("/weather/{location}")
    public Weather getWeather(@PathVariable("location") String location,
                              @RequestParam(name = "units", defaultValue = "celsius", required = false) String units) {
        Weather weather = weatherService.getWeather(location, TempUnitsEnum.valueOf(units.toLowerCase()).label, apiKey);
        return weather;
    }

    @GetMapping("/geolocation/{location}")
    public Coordinates getGeoLocation(@PathVariable("location") String location) {
        Coordinates coordinates = weatherService.getCoordinates(location, apiKey);
        return coordinates;
    }
}
