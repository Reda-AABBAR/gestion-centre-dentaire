package com.reda.prescriptionservice.service.impl;

import com.reda.prescriptionservice.dto.DossierMedicalDetails;
import com.reda.prescriptionservice.dto.PrescriptionRequest;
import com.reda.prescriptionservice.dto.PrescriptionResponse;
import com.reda.prescriptionservice.exception.ResourceNotFoundException;
import com.reda.prescriptionservice.model.Prescription;
import com.reda.prescriptionservice.repository.PrescriptionRepository;
import com.reda.prescriptionservice.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final DossierMedicalClient dossierMedicalClient;

    @Override
    public PrescriptionResponse createPrescription(PrescriptionRequest request) {
        DossierMedicalDetails dossierMedical = dossierMedicalClient.getDossierMedical(request.getDossierMedicalId());
        if (dossierMedical == null) {
            throw new ResourceNotFoundException("Dossier medical not found with id: " + request.getDossierMedicalId());
        }

        Prescription prescription = Prescription.builder()
                .datePrescription(request.getDatePrescription())
                .medicaments(request.getMedicaments())
                .dossierMedicalId(request.getDossierMedicalId())
                .build();

        Prescription savedPrescription = prescriptionRepository.save(prescription);
        log.info("Created prescription with ID: {}", savedPrescription.getId());

        return buildPrescriptionResponse(savedPrescription, dossierMedical);
    }

    @Override
    public PrescriptionResponse getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));

        DossierMedicalDetails dossierMedical = dossierMedicalClient.getDossierMedical(prescription.getDossierMedicalId());

        return buildPrescriptionResponse(prescription, dossierMedical);
    }

    private PrescriptionResponse buildPrescriptionResponse(Prescription prescription, DossierMedicalDetails dossierMedical) {
        return PrescriptionResponse.builder()
                .id(prescription.getId())
                .datePrescription(prescription.getDatePrescription())
                .medicaments(prescription.getMedicaments())
                .dossierMedical(dossierMedical)
                .build();
    }
}

