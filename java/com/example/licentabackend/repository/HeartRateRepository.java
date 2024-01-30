package com.example.licentabackend.repository;

import com.example.licentabackend.model.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRateRepository extends JpaRepository<HeartRate, Long> {
}
