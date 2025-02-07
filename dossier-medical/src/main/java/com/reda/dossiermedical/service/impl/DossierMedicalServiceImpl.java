package com.reda.dossiermedical.service.impl;

import com.reda.dossiermedical.dto.PatientDTO;
import com.reda.dossiermedical.model.DossierMedical;
import com.reda.dossiermedical.repository.DossierMedicalRepository;
import com.reda.dossiermedical.service.IDossierMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DossierMedicalServiceImpl implements IDossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final RestTemplate restTemplate;

    /**
     * Fetch full patient details from PatientService using the provided patient ID.
     */
    private PatientDTO fetchPatient(Long patientID) {
        String url = "http://patient-service/api/patients/{id}";
        return restTemplate.getForObject(url, PatientDTO.class, patientID);
    }

    /**
     * Enrich the dossier with complete patient information.
     * This method uses the persistent patientID field.
     */
    private DossierMedical enrichDossier(DossierMedical dossier) {
        if (dossier != null && dossier.getPatientID() != null) {
            PatientDTO patient = fetchPatient(dossier.getPatientID());
            dossier.setPatient(patient);
        }
        return dossier;
    }

    @Override
    public List<DossierMedical> getAllDossiers() {
        List<DossierMedical> list = dossierMedicalRepository.findAll();
        list.forEach(this::enrichDossier);
        return list;
    }

    @Override
    public Optional<DossierMedical> getDossierById(Long id) {
        Optional<DossierMedical> optionalDossier = dossierMedicalRepository.findById(id);
        optionalDossier.ifPresent(this::enrichDossier);
        return optionalDossier;
    }

    @Override
    public DossierMedical createDossier(DossierMedical dossier) {
        // Save the dossier with the patientID field provided.
        DossierMedical saved = dossierMedicalRepository.save(dossier);
        return enrichDossier(saved);
    }

    @Override
    public DossierMedical updateDossier(Long id, DossierMedical dossierDetails) {
        DossierMedical existingDossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier medical not found with id: " + id));

        existingDossier.setObservations(dossierDetails.getObservations());
        // Update the patientID if provided.
        if (dossierDetails.getPatientID() != null) {
            existingDossier.setPatientID(dossierDetails.getPatientID());
        }

        DossierMedical updated = dossierMedicalRepository.save(existingDossier);
        return enrichDossier(updated);
    }

    @Override
    public void deleteDossier(Long id) {
        dossierMedicalRepository.deleteById(id);
    }
}
