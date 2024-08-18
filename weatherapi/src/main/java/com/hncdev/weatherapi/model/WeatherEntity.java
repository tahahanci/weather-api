package com.hncdev.weatherapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String requestedCityName;
    private String cityName;
    private String countryName;
    private Integer temperature;
    private LocalDateTime updatedTime;
    private LocalDateTime responseLocalTime;

    public WeatherEntity() {
    }

    public WeatherEntity(String requestedCityName, String cityName, String countryName, Integer temperature,
                         LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.id = "";
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    public WeatherEntity(String id, String requestedCityName, String cityName, String countryName, Integer temperature,
                         LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this(requestedCityName, cityName, countryName, temperature, updatedTime, responseLocalTime);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
