package com.example.licentabackend.service;

import com.example.licentabackend.model.BloodPressure;
import com.example.licentabackend.repository.BloodPressureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodPressureServiceImplementation implements BloodPressureService{
    private final BloodPressureRepository bloodPressureRepository;

    public BloodPressureServiceImplementation(BloodPressureRepository bloodPressureRepository) {
        this.bloodPressureRepository = bloodPressureRepository;
    }

    public BloodPressure addBloodPressure(BloodPressure bloodPressure) {
        return bloodPressureRepository.save(bloodPressure);
    }

    public BloodPressure updateBloodPressure(Long id, BloodPressure bloodPressure) {
        Optional<BloodPressure> bloodPressure1 = bloodPressureRepository.findById(id);
        if (bloodPressure1.isPresent()){
            BloodPressure h = bloodPressure1.get();
            h.setValue(bloodPressure.getValue());
            h.setCollectedOn(bloodPressure.getCollectedOn());
            return bloodPressureRepository.save(h);
        }
        return bloodPressureRepository.save(bloodPressure);

    }

    @Override
    public Optional<BloodPressure> findById(Long id) {
        return bloodPressureRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        bloodPressureRepository.deleteById(id);
    }

    @Override
    public List<BloodPressure> getAll() {
        return bloodPressureRepository.findAll();
    }


}
