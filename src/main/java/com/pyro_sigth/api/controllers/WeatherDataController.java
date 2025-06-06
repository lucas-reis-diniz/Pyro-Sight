package com.pyro_sigth.api.controllers;

import com.pyro_sigth.api.dtos.weather_data.WeatherDataRequestDTO;
import com.pyro_sigth.api.dtos.weather_data.WeatherDataResponseDTO;
import com.pyro_sigth.api.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/weathers-data")
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    @PostMapping
    public ResponseEntity<WeatherDataResponseDTO> save(@RequestBody WeatherDataRequestDTO body) {
        WeatherDataResponseDTO weatherData = this.weatherDataService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherData);
    }

    @GetMapping
    public ResponseEntity<List<WeatherDataResponseDTO>> findAll() {
        List<WeatherDataResponseDTO> weathersData = this.weatherDataService.findAll();
        return ResponseEntity.ok(weathersData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherDataResponseDTO> findById(@PathVariable String id) {
        WeatherDataResponseDTO weatherData = this.weatherDataService.findById(UUID.fromString(id));

        if (weatherData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(weatherData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherDataResponseDTO> update(@PathVariable String id, @RequestBody WeatherDataRequestDTO body) {
        WeatherDataResponseDTO weatherData = this.weatherDataService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(weatherData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        WeatherDataResponseDTO weatherData = this.weatherDataService.findById(UUID.fromString(id));

        if (weatherData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.weatherDataService.delete(weatherData.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
