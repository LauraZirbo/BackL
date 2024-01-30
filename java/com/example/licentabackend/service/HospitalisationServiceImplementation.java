package com.example.licentabackend.service;

import com.example.licentabackend.model.Diagnosis;
import com.example.licentabackend.model.Hospitalisation;
import com.example.licentabackend.model.Medication;
import com.example.licentabackend.repository.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalisationServiceImplementation implements HospitalisationService {

    private final HospitalisationRepository hospitalisationRepository;
    private final PulseRepository pulseRepository;
    private final TemperatureRepository temperatureRepository;
    private final HeartRateRepository heartRateRepository;
    private final BloodPressureRepository bloodPressureRepository;

    public HospitalisationServiceImplementation(HospitalisationRepository hospitalisationRepository, PulseRepository pulseRepository, TemperatureRepository temperatureRepository, HeartRateRepository heartRateRepository, BloodPressureRepository bloodPressureRepository) {
        this.hospitalisationRepository = hospitalisationRepository;
        this.pulseRepository = pulseRepository;
        this.temperatureRepository = temperatureRepository;
        this.heartRateRepository = heartRateRepository;
        this.bloodPressureRepository = bloodPressureRepository;
    }

    public Hospitalisation addHospitalisation(Hospitalisation hospitalisation) {
        return hospitalisationRepository.save(hospitalisation);
    }

    public Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation) {
        Optional<Hospitalisation> hospitalisation1 = hospitalisationRepository.findById(id);
        if (hospitalisation1.isPresent()) {
            Hospitalisation h = hospitalisation1.get();
            h.setAdmissionDate(hospitalisation.getAdmissionDate());
            h.setDischargeDate(hospitalisation.getDischargeDate());
            h.setBloodPressureList(hospitalisation.getBloodPressureList());
            h.setHeartRateList(hospitalisation.getHeartRateList());
            h.setTemperatureList(hospitalisation.getTemperatureList());
            h.setPulseList(hospitalisation.getPulseList());
            h.setDiagnosisList(hospitalisation.getDiagnosisList());
            h.setMedicationList(hospitalisation.getMedicationList());
            return hospitalisationRepository.save(h);
        }
        return hospitalisationRepository.save(hospitalisation);

    }

    @Override
    public Hospitalisation addHospitalisationDiagnosis(Long id, List<Diagnosis> diagnosisList) {
        Optional<Hospitalisation> hospitalisation1 = hospitalisationRepository.findById(id);
        if (hospitalisation1.isPresent()) {
            Hospitalisation h = hospitalisation1.get();
            h.setDiagnosisList(diagnosisList);
            return hospitalisationRepository.save(h);
        }
        return null;
    }

    @Override
    public Hospitalisation addHospitalisationMedication(Long id, List<Medication> medicationList) {
        Optional<Hospitalisation> hospitalisation1 = hospitalisationRepository.findById(id);
        if (hospitalisation1.isPresent()) {
            Hospitalisation h = hospitalisation1.get();
            h.setMedicationList(medicationList);
            return hospitalisationRepository.save(h);
        }
        return null;
    }

    @Override
    public Optional<Hospitalisation> findById(Long id) {

        return hospitalisationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        hospitalisationRepository.deleteById(id);
    }

    @Override
    public List<Hospitalisation> getAll() {
        return hospitalisationRepository.findAll();
    }

    public List<Hospitalisation> getLastHospitalisationOfAHospital(Long hospitalId) {
        List<Hospitalisation> hospitalisations = hospitalisationRepository.findAll().stream().filter(hospitalisation -> (Objects.equals(hospitalisation.getHospital().getHospitalId(), hospitalId)) && (hospitalisation.getDischargeDate() == null)).collect(Collectors.toList());
        return hospitalisations;
    }

    @Override
    public Hospitalisation dischargePatient(Long id, Date date) {
        Optional<Hospitalisation> hospitalisation1 = hospitalisationRepository.findById(id);
        if (hospitalisation1.isPresent()) {
            Hospitalisation h = hospitalisation1.get();
            h.setDischargeDate(date);
            return hospitalisationRepository.save(h);
        }
        return null;
    }


}
