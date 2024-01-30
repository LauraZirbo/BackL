package com.example.licentabackend.service;

import com.example.licentabackend.model.Hospital;
import com.example.licentabackend.model.Medication;
import com.example.licentabackend.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImplementation implements MedicationService{
    private final MedicationRepository medicationRepository;

    public MedicationServiceImplementation(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication updateMedication(Long id, Medication medication) {
        Optional<Medication> medication1 = medicationRepository.findById(id);
        if (medication1.isPresent()){
            Medication h = medication1.get();
            h.setName(medication.getName());
            return medicationRepository.save(h);
        }
        return medicationRepository.save(medication);

    }

    @Override
    public Optional<Medication> findById(Long id) {
        return medicationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        medicationRepository.deleteById(id);
    }

    @Override
    public List<Medication> getAll() {
        return medicationRepository.findAll();
    }

}
