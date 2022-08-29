package com.etica.weather.service;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.Weather;
import feign.FeignException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static com.etica.weather.service.WeatherService.LIMIT_DEFAULT;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherServiceTest {

    @Mock
    private GeocodingClient geocodingClient;

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private WeatherService weatherService;

    private String location;
    private String apiKey;
    private String unit;

    @Before
    public void setUp() {
        location = "Melbourne";
        apiKey = "anyapikey123";
        unit = "celsius";
    }

    @Test
    public void shouldReturnWeatherWhenOnValidInput() {
        Weather weather = new Weather();
        weather.setLocation(location);
        weather.setUnit(unit);
        when(weatherClient.getWeather(location, unit, apiKey)).thenReturn((weather));

        Weather weatherResult = weatherService.getWeather(location, unit, apiKey);

        assertThat(weatherResult.getUnit(), is(unit));
        assertThat(weatherResult.getLocation(), is(location));
    }

    @Test
    public void shouldReturnNullWhenFeignExceptionOccurred() {
        when(weatherClient.getWeather(location, unit, apiKey)).thenThrow(FeignException.class);

        assertThat(weatherService.getWeather(location, unit, apiKey), is(nullValue()));
    }

    @Test
    public void shouldReturnCoordinatesOnValidInput() {
        Coordinates coordinates = new Coordinates();
        coordinates.setName(location);
        when(geocodingClient.getCoordinates(location, apiKey, LIMIT_DEFAULT)).thenReturn(Arrays.asList(coordinates));

        Coordinates coordinatesResult = weatherService.getCoordinates(location, apiKey);

        assertThat(weatherService.getCoordinates(location, apiKey), is(notNullValue()));
        assertThat(coordinatesResult.getName(), is(location));
    }
}
