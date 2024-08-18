package com.hncdev.weatherapi.controller;

import com.hncdev.weatherapi.dto.WeatherDto;
import com.hncdev.weatherapi.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
public class WeatherAPI {

    private final WeatherService weatherService;

    public WeatherAPI(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("cityName") String cityName) {
        return ResponseEntity.ok(weatherService.getWeatherByCityName(cityName));
    }
}
