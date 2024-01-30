package com.example.licentabackend.service;

import com.example.licentabackend.model.Diagnosis;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {
    Diagnosis addDiagnosis(Diagnosis diagnosis);

    Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis);

    Optional<Diagnosis> findById(Long id);

    void deleteById(Long id);

    List<Diagnosis> getAll();
}
