package com.hncdev.weatherapi.dto;

public record WeatherResponse(Request request, Location location, Current current) {
}
