package com.example.licentabackend.service;

import com.example.licentabackend.model.Temperature;

import java.util.List;
import java.util.Optional;

public interface TemperatureService {
    Temperature addTemperature(Temperature temperature);

    Temperature updateTemperature(Long id, Temperature temperature);

    Optional<Temperature> findById(Long id);

    void deleteById(Long id);

    List<Temperature> getAll();
}
