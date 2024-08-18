package com.hncdev.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hncdev.weatherapi.model.WeatherEntity;

import java.time.LocalDateTime;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedTime) {

    public static WeatherDto convert(WeatherEntity from) {
        return new WeatherDto(from.getCityName(), from.getCountryName(), from.getTemperature(), from.getUpdatedTime());
    }
}
