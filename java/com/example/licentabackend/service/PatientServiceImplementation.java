package com.example.licentabackend.service;

import com.example.licentabackend.model.Patient;
import com.example.licentabackend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplementation implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> patient1 = patientRepository.findById(id);
        if (patient1.isPresent()){
            Patient h = patient1.get();
            h.setCnp(patient.getCnp());
            h.setName(patient.getName());
            h.setHospitalisationsList(patient.getHospitalisationsList());
            h.setAge(patient.getAge());
            h.setBirthday(patient.getBirthday());
            h.setAlergies(patient.getAlergies());
            return patientRepository.save(h);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findByCnp(String cnp) {
        return patientRepository.findByCnp(cnp);
    }
}
