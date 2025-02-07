package com.reda.prescriptionservice.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class PrescriptionResponse {
    private Long id;
    private LocalDate datePrescription;
    private String medicaments;
    private DossierMedicalDetails dossierMedical;
}
