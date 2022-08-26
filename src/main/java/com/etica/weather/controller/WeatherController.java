package com.etica.weather.controller;

import com.etica.weather.model.Coordinates;
import com.etica.weather.model.TempUnitsEnum;
import com.etica.weather.model.Weather;
import com.etica.weather.model.WeatherResponse;
import com.etica.weather.service.WeatherService;
import com.etica.weather.util.WeatherResponseMapper;
import feign.Param;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "WeatherController", description = "Manages weather forecast operations")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherResponseMapper weatherResponseMapper;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Operation(description = "Gets weather forecast",
        parameters = {  @Parameter(in = ParameterIn.PATH, name = "location", description = "City name and/or state code and/or country code divided by comma. " +
                "Please note that searching by states available only for the USA locations"),
                        @Parameter(in = ParameterIn.QUERY, name = "units", description = "Temperature units")},
        responses = {   @ApiResponse(responseCode = "200", description = "Successful retrieval of weather forecast details",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Bad Request. Invalid values of temperature unit"),
                        @ApiResponse(responseCode = "404", description = "Location NOT found")})
    @GetMapping("/weather/{location}")
    public ResponseEntity getWeather(@PathVariable(name = "location") String location,
                                     @RequestParam(name = "units", defaultValue = "celsius", required = false) String units) {
        if(!EnumUtils.isValidEnum(TempUnitsEnum.class, units)) {
            return ResponseEntity.badRequest()
                    .body(String.format("Invalid values of temperature unit. Valid values are [%s]",
                            StringUtils.join(TempUnitsEnum.values(), ", ")));
        }
        Weather weather = weatherService.getWeather(location, TempUnitsEnum.valueOf(units.toLowerCase()).label, apiKey);

        if(ObjectUtils.isNotEmpty(weather)) {
            return ResponseEntity.ok().body(weatherResponseMapper.map(weather));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Gets location coordinates",
        parameters = {  @Parameter(in = ParameterIn.PATH, name = "location", description = "City name and/or state code and/or country code divided by comma. " +
                "Please note that searching by states available only for the USA locations")},
        responses = {   @ApiResponse(responseCode = "200", description = "Successful retrieval location coordinates",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coordinates.class))),
                @ApiResponse(responseCode = "404", description = "Location NOT found")})
    @GetMapping("/geolocation/{location}")
    public Coordinates getGeoLocation(@PathVariable("location") String location) {
        Coordinates coordinates = weatherService.getCoordinates(location, apiKey);
        return coordinates;
    }
}
