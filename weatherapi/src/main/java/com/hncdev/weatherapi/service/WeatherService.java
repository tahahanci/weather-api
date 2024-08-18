package com.hncdev.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hncdev.weatherapi.dto.WeatherDto;
import com.hncdev.weatherapi.dto.WeatherResponse;
import com.hncdev.weatherapi.model.WeatherEntity;
import com.hncdev.weatherapi.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static String API_URL = "http://api.weatherstack.com/current?access_key=2350c51d6721a9ccd65b4d26d4e8f581&query=";

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    public WeatherDto getWeatherByCityName(String cityName) {
        Optional<WeatherEntity> optionalWeatherEntity = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(cityName);
        return optionalWeatherEntity.map(WeatherDto::convert).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(cityName)));
    }

    private WeatherEntity getWeatherFromWeatherStack(String cityName) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL + cityName, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(cityName, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherEntity weatherEntity = new WeatherEntity(
                city, weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localtime(), dateTimeFormatter)
        );

        return weatherRepository.save(weatherEntity);
    }
}
