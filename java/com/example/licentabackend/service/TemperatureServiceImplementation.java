package com.example.licentabackend.service;

import com.example.licentabackend.model.Temperature;
import com.example.licentabackend.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureServiceImplementation implements TemperatureService {
    private final TemperatureRepository temperatureRepository;

    public TemperatureServiceImplementation(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public Temperature addTemperature(Temperature temperature) {
        return temperatureRepository.save(temperature);
    }

    @Override
    public Temperature updateTemperature(Long id, Temperature temperature) {
        Optional<Temperature> temperature1 = temperatureRepository.findById(id);
        if (temperature1.isPresent()){
            Temperature h = temperature1.get();
            h.setCollectedOn(temperature.getCollectedOn());
            h.setHospitalisation(temperature.getHospitalisation());
            h.setValue(temperature.getValue());
            return temperatureRepository.save(h);
        }
        return temperatureRepository.save(temperature);
    }


    @Override
    public Optional<Temperature> findById(Long id) {
        return temperatureRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        temperatureRepository.deleteById(id);
    }

    @Override
    public List<Temperature> getAll() {
        return temperatureRepository.findAll();
    }
}
