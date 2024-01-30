package com.example.licentabackend.repository;

import com.example.licentabackend.model.Hospital;
import com.example.licentabackend.model.Hospitalisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalisationRepository extends JpaRepository<Hospitalisation, Long> {
}
