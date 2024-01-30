package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medicalStaff")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicalStaffId;

    @Column(nullable = false, length = 256)
    private String Name;

    @Column(nullable = false, length = 256)
    private String jobTitle;

    @Column(nullable = false, length = 256)
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"medicalStaffList"})
    @JoinColumn(nullable = false, name = "hospital_id")
    private Hospital hospital;

//    @ManyToMany
//    @JoinTable(
//            name = "medicalStaffWorkedHere",
//            joinColumns = @JoinColumn(name = "medicalStaff_id"),
//            inverseJoinColumns = @JoinColumn(name = "hospitalisation_Id"))
//    private List<Hospitalisation> hospitalisationsWorkedList;


}
