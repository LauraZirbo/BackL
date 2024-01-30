package com.example.licentabackend.service;

import com.example.licentabackend.model.HeartRate;

import java.util.List;
import java.util.Optional;

public interface HeartRateService {
    HeartRate addHeartRate(HeartRate heartRate);

    HeartRate updateHeartRate(Long id, HeartRate heartRate);

    Optional<HeartRate> findById(Long id);

    void deleteById(Long id);

    List<HeartRate> getAll();
}
