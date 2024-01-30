package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, length = 256)
    private Long patientId;

    @Column(nullable = false)
    private String cnp;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private int age;

    @ElementCollection
    private List<String> alergies;

    @JsonManagedReference(value="patient-hospitalisation")
    @OneToMany(mappedBy="patient",cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"hospitalisations"})
    private List<Hospitalisation> hospitalisationsList;
}
