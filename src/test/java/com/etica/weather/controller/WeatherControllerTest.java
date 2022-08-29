package com.etica.weather.controller;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.TempUnitsEnum;
import com.etica.weather.model.Temperature;
import com.etica.weather.model.Weather;
import com.etica.weather.model.WeatherResponse;
import com.etica.weather.service.WeatherService;
import com.etica.weather.util.WeatherResponseMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private WeatherResponseMapper weatherResponseMapper;

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;
    private String location;
    private String apiKey;

    @Before
    public void setUp() {
        location = "Melbourne";
        apiKey = "anyapikey123";

        mockMvc = standaloneSetup(weatherController).build();
        ReflectionTestUtils.setField(weatherController, "apiKey", apiKey);
    }

    @Test
    public void shouldReturn400WhenUnitIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weather/Melbourne?units=zelvin").characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid values of temperature unit. Valid values are " +
                        "[kelvin, celsius, fahrenheit]"));
    }

    @Test
    public void shouldReturn404WhenWeatherClientReturnNoValue() throws Exception {
        when(weatherService.getWeather(location, "celsius", apiKey)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/Melbourne").characterEncoding("utf-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn200OnHappyPath() throws Exception {
        String unit = "fahrenheit";
        String temp = "20";
        String tempMin = "15";
        String tempMax = "25";

        Weather weather = new Weather();
        weather.setLocation(location);
        weather.setUnit(unit);
        Temperature temperature = new Temperature();
        temperature.setTemp(temp);
        temperature.setTempMin(tempMin);
        temperature.setTempMin(tempMax);
        weather.setTemperature(temperature);
        when(weatherService.getWeather(location, TempUnitsEnum.valueOf(unit.toLowerCase()).label, apiKey)).thenReturn(weather);

        WeatherResponse response = new WeatherResponse();
        response.setLocation(location);
        response.setTempUnit(unit);
        response.setTemperature(temp);
        response.setTempMin(tempMin);
        response.setTempMax(tempMax);
        when(weatherResponseMapper.map(any(Weather.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/Melbourne?units=fahrenheit").characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location", is(location)))
                .andExpect(jsonPath("$.tempUnit", is(unit)))
                .andExpect(jsonPath("$.temperature", is(temp)))
                .andExpect(jsonPath("$.tempMin", is(tempMin)))
                .andExpect(jsonPath("$.tempMax", is(tempMax)));
    }

    @Test
    public void shouldReturn200WhenGetCoordinates() throws Exception {
        Coordinates coordinates = new Coordinates();
        coordinates.setName(location);
        when(weatherService.getCoordinates(location, apiKey)).thenReturn(coordinates);

        mockMvc.perform(MockMvcRequestBuilders.get("/geolocation/Melbourne").characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(location)));
    }
}
