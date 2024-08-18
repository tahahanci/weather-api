package com.hncdev.weatherapi.repository;

import com.hncdev.weatherapi.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
}
