package com.example.licentabackend.service;

import com.example.licentabackend.model.Diagnosis;
import com.example.licentabackend.model.Hospitalisation;
import com.example.licentabackend.model.Medication;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HospitalisationService {

    Hospitalisation addHospitalisation(Hospitalisation hospital);

    Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation);

    Hospitalisation addHospitalisationDiagnosis(Long id, List<Diagnosis> diagnosisList);

    Hospitalisation addHospitalisationMedication(Long id, List<Medication> medicationList);

    Optional<Hospitalisation> findById(Long id);

    void deleteById(Long id);

    List<Hospitalisation> getAll();
    List<Hospitalisation> getLastHospitalisationOfAHospital(Long hospitalId);

    Hospitalisation dischargePatient(Long id, Date date);

}
