package com.reda.prescriptionservice.dto;

import lombok.*;

@Data
@Builder
public class DossierMedicalDetails {
    private Long id;
    private String observations;
    private PatientDetails patient;
}