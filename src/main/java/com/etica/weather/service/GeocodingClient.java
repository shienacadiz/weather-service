package com.etica.weather.service;

import com.etica.weather.model.Coordinates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "geocoding", url = "${geocoding.url}")
public interface GeocodingClient {

    @GetMapping("direct")
    List<Coordinates> getCoordinates(@RequestParam(name = "q") String q,
                                     @RequestParam(name = "appId") String apiKey,
                                     @RequestParam(required = false, defaultValue="1") int limit);
}
