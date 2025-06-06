package com.pyro_sigth.api.services;

import com.pyro_sigth.api.domains.weather_data.WeatherData;
import com.pyro_sigth.api.dtos.weather_data.WeatherDataRequestDTO;
import com.pyro_sigth.api.dtos.weather_data.WeatherDataResponseDTO;
import com.pyro_sigth.api.repositories.WeatherDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WeatherDataService {

    private final WeatherDataRepository weatherDataRepository;

    public WeatherDataService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    private WeatherDataResponseDTO toResponseDTO(WeatherData weatherData) {
        WeatherDataResponseDTO dto = new WeatherDataResponseDTO();

        dto.setId(weatherData.getId());
        dto.setHumidityPercent(weatherData.getHumidityPercent());
        dto.setTemperatureCelsius(weatherData.getTemperatureCelsius());
        dto.setPressureHpa(weatherData.getPressureHpa());
        dto.setWindSpeedKmh(weatherData.getWindSpeedKmh());
        dto.setRecordedAt(weatherData.getRecordedAt());

        return dto;
    }

    public WeatherDataResponseDTO save(WeatherDataRequestDTO body) {
        WeatherData newWeatherData = new WeatherData();

        newWeatherData.setTemperatureCelsius(body.getTemperatureCelsius());
        newWeatherData.setHumidityPercent(body.getHumidityPercent());
        newWeatherData.setWindSpeedKmh(body.getWindSpeedKmh());
        newWeatherData.setPressureHpa(body.getPressureHpa());
        newWeatherData.setRecordedAt(body.getRecordedAt());

        WeatherData savedWeatherData = weatherDataRepository.save(newWeatherData);

        return toResponseDTO(savedWeatherData);
    }

    public List<WeatherDataResponseDTO> findAll() {
        List<WeatherData> weatherDatas = weatherDataRepository.findAll();
        return weatherDatas.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public WeatherDataResponseDTO findById(UUID id) {
        WeatherData weatherData = weatherDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WeatherData not found"));

        return toResponseDTO(weatherData);
    }

    public WeatherDataResponseDTO update(String id, WeatherDataRequestDTO body) {
        WeatherData weatherData = weatherDataRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("WeatherData not found"));

        weatherData.setTemperatureCelsius(body.getTemperatureCelsius());
        weatherData.setHumidityPercent(body.getHumidityPercent());
        weatherData.setWindSpeedKmh(body.getWindSpeedKmh());
        weatherData.setPressureHpa(body.getPressureHpa());
        weatherData.setRecordedAt(body.getRecordedAt());

        WeatherData updatedWeatherData = weatherDataRepository.save(weatherData);
        return toResponseDTO(updatedWeatherData);
    }

    public void delete(UUID id) {
        WeatherData weatherData = weatherDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WeatherData not found"));
        weatherDataRepository.delete(weatherData);
    }
}
