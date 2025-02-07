package com.reda.prescriptionservice.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRequest {
    @NotNull
    private LocalDate datePrescription;

    private String medicaments;

    @NotNull
    private Long dossierMedicalId;
}
