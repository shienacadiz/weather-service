package com.etica.weather.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Location coordinate details")
public class Coordinates implements Serializable {

    @Schema(description = "Location name", example = "Auckland")
    private String name;

    @Schema(description = "Latitude coordinates", example = "-36.852095")
    private String lat;

    @Schema(description = "Longitude coordinates", example = "174.7631803")
    private String lon;

    @Schema(description = "Country", example = "NZ")
    private String country;

    @Schema(description = "State", example = "Auckland")
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
