package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "hospital")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospital {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long hospitalId;

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String city;

    @Column(nullable = false, length = 256)
    private String type;

    @OneToMany(mappedBy="hospital",cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"hospitalisations"})
    private List<Hospitalisation> hospitalisations;

    @OneToMany(mappedBy="hospital",cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"hospital"})
    private List<MedicalStaff> medicalStaffList;
}
