package com.reda.dossiermedical.model;

import com.reda.dossiermedical.dto.PatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "dossier_medical")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Persistent field to store the patient ID.
    private Long patientID;

    private String observations;

    // Transient field used to hold full patient information (not persisted).
    @Transient
    private PatientDTO patient;
}
