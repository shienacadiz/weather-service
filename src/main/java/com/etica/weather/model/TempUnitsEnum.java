package com.etica.weather.model;

public enum TempUnitsEnum {
    kelvin("standard"),
    celsius("metric"),
    fahrenheit("imperial");

    public final String label;

    private TempUnitsEnum(String label) {
        this.label = label;
    }
}
