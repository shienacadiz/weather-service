package com.etica.weather.controller;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.TempUnitsEnum;
import com.etica.weather.model.Weather;
import com.etica.weather.service.WeatherService;
import com.etica.weather.util.WeatherResponseMapper;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherResponseMapper weatherResponseMapper;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @GetMapping("/weather/{location}")
    public ResponseEntity getWeather(@PathVariable("location") String location,
                                     @RequestParam(name = "units", defaultValue = "celsius", required = false) String units) {
        if(!EnumUtils.isValidEnum(TempUnitsEnum.class, units)) {
            return ResponseEntity.badRequest()
                    .body(String.format("Invalid values of temperature unit. Valid values are [%s]",
                            StringUtils.join(TempUnitsEnum.values(), ", ")));
        }
        Weather weather = weatherService.getWeather(location, TempUnitsEnum.valueOf(units.toLowerCase()).label, apiKey);
        return ResponseEntity.ok().body(weatherResponseMapper.map(weather));
    }

    @GetMapping("/geolocation/{location}")
    public Coordinates getGeoLocation(@PathVariable("location") String location) {
        Coordinates coordinates = weatherService.getCoordinates(location, apiKey);
        return coordinates;
    }
}
