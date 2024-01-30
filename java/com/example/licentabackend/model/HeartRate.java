package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "heartRate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 256)
    private Float value;

    @Column(nullable = false, length = 256)
    private Date collectedOn;

    @JsonBackReference(value = "heartRate-hospitalisation")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"heartRate"})
    @JoinColumn(nullable = false, name = "hospitalisation_id")
    private Hospitalisation hospitalisation;

}
