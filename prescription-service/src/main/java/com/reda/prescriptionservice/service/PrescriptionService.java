package com.reda.prescriptionservice.service;

import com.reda.prescriptionservice.dto.PrescriptionRequest;
import com.reda.prescriptionservice.dto.PrescriptionResponse;

public interface PrescriptionService {
    PrescriptionResponse createPrescription(PrescriptionRequest request);
    PrescriptionResponse getPrescriptionById(Long id);
}
