package com.example.licentabackend.service;

import com.example.licentabackend.model.Pulse;
import com.example.licentabackend.repository.PulseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PulseServiceImplementation implements PulseService {
    private final PulseRepository pulseRepository;

    public PulseServiceImplementation(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    public Pulse addPulse(Pulse pulse) {
        return pulseRepository.save(pulse);
    }

    public Pulse updatePulse(Long id, Pulse pulse) {
        Optional<Pulse> pulse1 = pulseRepository.findById(id);
        if (pulse1.isPresent()){
            Pulse h = pulse1.get();
            ///TobeRemade
            return pulseRepository.save(h);
        }
        return pulseRepository.save(pulse);

    }

    @Override
    public Optional<Pulse> findById(Long id) {
        return pulseRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        pulseRepository.deleteById(id);
    }

    @Override
    public List<Pulse> getAll() {
        return pulseRepository.findAll();
    }


}
