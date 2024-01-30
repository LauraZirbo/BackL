package com.example.licentabackend.service;

import com.example.licentabackend.model.Medication;

import java.util.List;
import java.util.Optional;

public interface MedicationService {
    Medication addMedication(Medication medication);

    Medication updateMedication(Long id, Medication medication);

    Optional<Medication> findById(Long id);

    void deleteById(Long id);

    List<Medication> getAll();
}
