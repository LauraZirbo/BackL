package com.example.licentabackend.service;

import com.example.licentabackend.model.BloodPressure;
import com.example.licentabackend.model.Diagnosis;
import com.example.licentabackend.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImplementation implements DiagnosisService{

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImplementation(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis) {
        Optional<Diagnosis> diagnosis1 = diagnosisRepository.findById(id);
        if (diagnosis1.isPresent()){
            Diagnosis h = diagnosis1.get();
            h.setName(diagnosis.getName());
            return diagnosisRepository.save(h);
        }
        return diagnosisRepository.save(diagnosis);

    }

    @Override
    public Optional<Diagnosis> findById(Long id) {
        return diagnosisRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        diagnosisRepository.deleteById(id);
    }

    @Override
    public List<Diagnosis> getAll() {
        return diagnosisRepository.findAll();
    }

}
