package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hospitalisation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hospitalisationId;

    @Column(nullable = false)
    private Date admissionDate;

    @Column
    private Date dischargeDate;


    @JsonManagedReference(value = "heartRate-hospitalisation")
    @OneToMany(mappedBy = "hospitalisation", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"heartRateList"})
    private List<HeartRate> heartRateList;

    @JsonManagedReference(value = "pulse-hospitalisation")
    @OneToMany(mappedBy = "hospitalisation", cascade = CascadeType.MERGE,orphanRemoval = true)
    @JsonIgnoreProperties(value = {"pulseList"})
    private List<Pulse> pulseList;

    @JsonManagedReference(value = "temperature-hospitalisation")
    @OneToMany(mappedBy = "hospitalisation", cascade = CascadeType.MERGE,orphanRemoval = true)
    @JsonIgnoreProperties(value = {"temperatureList"})
    private List<Temperature> temperatureList;

    @JsonManagedReference(value = "bloodPressure-hospitalisation")
    @OneToMany(mappedBy = "hospitalisation", cascade = CascadeType.MERGE,orphanRemoval = true)
    @JsonIgnoreProperties(value = {"bloodPressureList"})
    private List<BloodPressure> bloodPressureList;



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hospitalisations"})
    @JoinColumn(nullable = false, name = "hospital_id")
    private Hospital hospital;

    @JsonBackReference(value = "patient-hospitalisation")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hospitalisations"})
    @JoinColumn(nullable = false, name = "patient_id")
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name = "diagnosedOnHospitalisation",
            joinColumns = @JoinColumn(name = "hospitalisation_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
    private List<Diagnosis> diagnosisList;

    @ManyToMany
    @JoinTable(
            name = "prescribedDuringHospitalisation",
            joinColumns = @JoinColumn(name = "hospitalisation_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medicationList;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "hospitalisationsWorkedList")
//    private List<MedicalStaff> medicalStaffList;
}
