package com.etica.weather.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class openApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Weather Forecast Service")
                        .description("Simple API to check the weather based on location")
                        .version("v0.0.1")
                        .contact(new Contact().email("shienacadiz@gmail.com").url("https://github.com/shienacadiz")));
    }

}
