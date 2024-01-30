package com.example.licentabackend.service;

import com.example.licentabackend.model.Pulse;

import java.util.List;
import java.util.Optional;

public interface PulseService {
    Pulse addPulse(Pulse pulse);

    Pulse updatePulse(Long id, Pulse pulse);

    Optional<Pulse> findById(Long id);

    void deleteById(Long id);

    List<Pulse> getAll();

}
