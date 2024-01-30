package com.example.licentabackend.service;

import com.example.licentabackend.model.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService {
    Hospital addHospital(Hospital hospital);

    Hospital updateHospital(Long id, Hospital hospital);

    Optional<Hospital> findById(Long id);

    void deleteById(Long id);

    List<Hospital> getAll();

}
