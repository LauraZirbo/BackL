package com.example.licentabackend.service;

import com.example.licentabackend.model.HeartRate;
import com.example.licentabackend.repository.HeartRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeartRateServiceImplementation implements HeartRateService {

    private final HeartRateRepository heartRateRepository;

    public HeartRateServiceImplementation(HeartRateRepository heartRateRepository) {
        this.heartRateRepository = heartRateRepository;
    }

    public HeartRate addHeartRate(HeartRate heartRate) {
        return heartRateRepository.save(heartRate);
    }

    public HeartRate updateHeartRate(Long id, HeartRate heartRate) {
        Optional<HeartRate> heartRate1 = heartRateRepository.findById(id);
        if (heartRate1.isPresent()){
            HeartRate h = heartRate1.get();
            ///TobeRemade
            return heartRateRepository.save(h);
        }
        return heartRateRepository.save(heartRate);

    }

    @Override
    public Optional<HeartRate> findById(Long id) {
        return heartRateRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        heartRateRepository.deleteById(id);
    }

    @Override
    public List<HeartRate> getAll() {
        return heartRateRepository.findAll();
    }


}
