package com.example.licentabackend.repository;

import com.example.licentabackend.model.MedicalStaff;
import com.example.licentabackend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {
    Optional<MedicalStaff> findByEmail(String email);

}
