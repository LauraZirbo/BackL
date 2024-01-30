package com.example.licentabackend.repository;

import com.example.licentabackend.model.BloodPressure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {
}
