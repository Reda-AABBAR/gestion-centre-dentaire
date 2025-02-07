package com.reda.prescriptionservice.model;

import com.reda.prescriptionservice.dto.DossierMedicalDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datePrescription;
    private String medicaments;
    private Long dossierMedicalId;

    @Transient
    private DossierMedicalDetails dossierMedical;
}
