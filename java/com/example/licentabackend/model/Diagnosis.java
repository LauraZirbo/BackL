package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "diagnosis")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diagnosisId;

    @Column(nullable = false, length = 256)
    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy = "diagnosisList")
    private List<Hospitalisation> diagnosedOn;
}
