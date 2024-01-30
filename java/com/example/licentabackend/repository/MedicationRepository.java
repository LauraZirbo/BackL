package com.example.licentabackend.repository;

import com.example.licentabackend.model.Medication;
import com.example.licentabackend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {


}
