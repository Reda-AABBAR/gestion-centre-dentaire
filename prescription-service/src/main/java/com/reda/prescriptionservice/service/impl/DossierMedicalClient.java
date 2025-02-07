package com.reda.prescriptionservice.service.impl;

import com.reda.prescriptionservice.dto.DossierMedicalDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign Client
@FeignClient(name = "dossier-medical-service", url = "${dossier-medical-service.url}")
public interface DossierMedicalClient {
    @GetMapping("/api/dossiers/{id}")
    DossierMedicalDetails getDossierMedical(@PathVariable Long id);
}
