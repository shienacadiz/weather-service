package com.etica.weather.util;

import com.etica.weather.model.Weather;
import com.etica.weather.model.WeatherResponse;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class WeatherResponseMapper {
    public WeatherResponse map(Weather weather) {
        WeatherResponse response = new WeatherResponse();
        response.setLocation(weather.getLocation());
        response.setCountry(weather.getSunTimeline().getCountry());
        response.setTemperature(weather.getTemperature().getTemp());
        response.setTempMin(weather.getTemperature().getTempMin());
        response.setTempMax(weather.getTemperature().getTempMax());
        response.setTempFeelsLike(weather.getTemperature().getFeelsLike());
        response.setHumidity(weather.getTemperature().getHumidity());
        response.setPressure(weather.getTemperature().getPressure());
        response.setDescriptionShort(weather.getWeather().get(0).getMain());
        response.setDescriptionLong(weather.getWeather().get(0).getDescription());
        response.setWeatherIcon(weather.getWeather().get(0).getIcon());

        ZoneOffset zoneOffset = WeatherUtil.convertUtcToZoneOffset(weather.getTimezone());
        response.setSunrise(WeatherUtil.convertUtcToLocalDateTime(weather.getSunTimeline().getSunrise(), zoneOffset));
        response.setSunset(WeatherUtil.convertUtcToLocalDateTime(weather.getSunTimeline().getSunset(), zoneOffset));

        return response;
    }
}
