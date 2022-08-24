package com.etica.weather.service;

import com.etica.weather.model.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather", url = "${weather.url}")
public interface WeatherClient {

    @GetMapping("weather")
    Weather getWeather(@RequestParam(name = "lat") String latitude,
                       @RequestParam(name = "lon") String longitude,
                       @RequestParam(name = "appId") String apiKey);
}
