package com.example.licentabackend.repository;

import com.example.licentabackend.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
