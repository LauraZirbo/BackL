package com.example.licentabackend.service;

import com.example.licentabackend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient addPatient(Patient patient);

    Patient updatePatient(Long id, Patient patient);

    Optional<Patient> findById(Long id);

    void deleteById(Long id);

    List<Patient> getAll();

    Optional<Patient> findByCnp(String cnp);

}
