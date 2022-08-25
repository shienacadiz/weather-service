package com.etica.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable {

    @JsonProperty("coord")
    private Coordinates coordinates;

    private List<WeatherDetails> weather;

    @JsonProperty("main")
    private Temperature temperature;

    @JsonProperty("sys")
    private SunTimeline sunTimeline;

    @JsonProperty("name")
    private String location;

    private long timezone;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<WeatherDetails> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDetails> weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public SunTimeline getSunTimeline() {
        return sunTimeline;
    }

    public void setSunTimeline(SunTimeline sunTimeline) {
        this.sunTimeline = sunTimeline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long timezone) {
        this.timezone = timezone;
    }
}
