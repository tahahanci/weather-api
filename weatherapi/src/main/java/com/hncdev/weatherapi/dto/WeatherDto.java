package com.hncdev.weatherapi.dto;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature) {}
