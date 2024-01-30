package com.example.licentabackend.service;

import com.example.licentabackend.model.Hospital;
import com.example.licentabackend.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImplementation implements HospitalService{
    private final HospitalRepository hospitalRepository;

    public HospitalServiceImplementation(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(Long id, Hospital hospital) {
        Optional<Hospital> hospital1 = hospitalRepository.findById(id);
        if (hospital1.isPresent()){
            Hospital h = hospital1.get();
            h.setName(hospital.getName());
            h.setCity(hospital.getCity());
            h.setType(hospital.getType());
            return hospitalRepository.save(h);
        }
        return hospitalRepository.save(hospital);

    }

    @Override
    public Optional<Hospital> findById(Long id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }
}
