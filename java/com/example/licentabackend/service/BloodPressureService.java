package com.example.licentabackend.service;

import com.example.licentabackend.model.BloodPressure;

import java.util.List;
import java.util.Optional;

public interface BloodPressureService {
    BloodPressure addBloodPressure(BloodPressure bloodPressure);

    BloodPressure updateBloodPressure(Long id, BloodPressure bloodPressure);

    Optional<BloodPressure> findById(Long id);

    void deleteById(Long id);

    List<BloodPressure> getAll();
}
