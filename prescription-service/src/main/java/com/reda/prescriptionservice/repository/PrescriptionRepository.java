package com.reda.prescriptionservice.repository;

import com.reda.prescriptionservice.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
