package com.etica.weather.config;

import com.etica.weather.controller.WeatherController;
import com.etica.weather.service.WeatherService;
import com.etica.weather.util.WeatherResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigTest {

    @Bean
    public WeatherController weatherController(){
        return new WeatherController();
    }

    @Bean
    public WeatherService weatherService(){
        return new WeatherService();
    }

    @Bean
    public WeatherResponseMapper weatherResponseMapper(){
        return new WeatherResponseMapper();
    }
}
