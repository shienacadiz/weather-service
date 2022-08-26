package com.etica.weather.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Weather forecast details")
public class WeatherResponse {

    @Schema(description = "Location name", example = "Melbourne")
    private String location;

    @Schema(description = "Country abbreviation", example = "AU")
    private String country;

    @Schema(description = "Temperature", example = "11.7")
    private String temperature;

    @Schema(description = "Minimum temperature", example = "11.21")
    private String tempMin;

    @Schema(description = "Maximum temperature", example = "12.34")
    private String tempMax;

    @Schema(description = "Temperature feels like", example = "11.17")
    private String tempFeelsLike;

    @Schema(description = "Units of temperature", example = "celsius")
    private String tempUnit;

    @Schema(description = "Humidity", example = "86")
    private String humidity;

    @Schema(description = "Pressure", example = "1029")
    private String pressure;

    @Schema(description = "Short weather description", example = "Clouds")
    private String descriptionShort;

    @Schema(description = "Long weather description", example = "broken clouds")
    private String descriptionLong;

    @Schema(description = "Weather icon. Use it as http://openweathermap.org/img/wn/{icon_id}@2x.png", example = "04n")
    private String weatherIcon;

    @Schema(description = "Sunrise timestamp", example = "2022-08-26T06:51:20")
    private LocalDateTime sunrise;

    @Schema(description = "Sunset timestamp", example = "2022-08-26T17:53:21")
    private LocalDateTime sunset;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempFeelsLike() {
        return tempFeelsLike;
    }

    public void setTempFeelsLike(String tempFeelsLike) {
        this.tempFeelsLike = tempFeelsLike;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }
}
