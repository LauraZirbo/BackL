package com.example.licentabackend.repository;

import com.example.licentabackend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByCnp(String cnp);

}
