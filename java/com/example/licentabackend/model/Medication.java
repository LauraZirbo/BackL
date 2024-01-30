package com.example.licentabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "medication")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicationId;

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String administrationMethod;

    @Column(nullable = false, length = 256)
    private String quantity;

//    @Column(nullable = false, length = 256)
//    private String period;

    @JsonIgnore
    @ManyToMany(mappedBy = "medicationList")
    private List<Hospitalisation> prescribedDuring;

}
