package com.etica.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Temperature implements Serializable {
    private String temp;

    @JsonProperty("feelsLike")
    private String feelsLike;

    @JsonProperty("temp_min")
    private String tempMin;

    @JsonProperty("temp_max")
    private String tempMax;

    private String pressure;

    private String humidity;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
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

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
