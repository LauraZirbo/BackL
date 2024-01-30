package com.example.licentabackend.repository;

import com.example.licentabackend.model.Pulse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PulseRepository extends JpaRepository<Pulse, Long> {
}
