package com.example.licentabackend.service;

import com.example.licentabackend.model.Hospital;
import com.example.licentabackend.model.MedicalStaff;
import com.example.licentabackend.repository.MedicalStaffRepository;
import com.example.licentabackend.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffServiceImplementation implements MedicalStaffService {

    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffServiceImplementation(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    @Override
    public MedicalStaff addMedicalStaff(MedicalStaff medicalStaff) {
        return medicalStaffRepository.save(medicalStaff);
    }

    @Override
    public MedicalStaff updateMedicalStaff(Long id, MedicalStaff medicalStaff) {
        Optional<MedicalStaff> medicalStaff1 = medicalStaffRepository.findById(id);
        if (medicalStaff1.isPresent()){
            MedicalStaff h = medicalStaff1.get();
            h.setName(medicalStaff.getName());
            h.setHospital(medicalStaff.getHospital());
            h.setJobTitle(medicalStaff.getJobTitle());
            return medicalStaffRepository.save(h);
        }
        return medicalStaffRepository.save(medicalStaff);    }

    @Override
    public Optional<MedicalStaff> findById(Long id) {
        return medicalStaffRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        medicalStaffRepository.deleteById(id);

    }

    @Override
    public List<MedicalStaff> getAll() {
        return medicalStaffRepository.findAll();
    }

    @Override
    public Optional<MedicalStaff> findByEmail(String email) {
        return medicalStaffRepository.findByEmail(email);
    }
}
