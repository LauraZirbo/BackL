package com.example.licentabackend.service;

import com.example.licentabackend.model.MedicalStaff;
import com.example.licentabackend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicalStaffService {
    MedicalStaff addMedicalStaff(MedicalStaff medicalStaff);

    MedicalStaff updateMedicalStaff(Long id, MedicalStaff medicalStaff);

    Optional<MedicalStaff> findById(Long id);

    void deleteById(Long id);

    List<MedicalStaff> getAll();

    Optional<MedicalStaff> findByEmail(String email);

}
