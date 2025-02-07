package com.reda.rendezvousservice.services.impl;

import com.reda.rendezvousservice.classes.Patient;
import com.reda.rendezvousservice.models.RendezVous;
import com.reda.rendezvousservice.repositories.RendezVousRepository;
import com.reda.rendezvousservice.services.IRendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RendezVousServiceImpl implements IRendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RestTemplate restTemplate;

    /**
     * Récupère les informations du patient depuis PatientService via Eureka.
     */
    private Patient fetchPatient(Long patientId) {
        String url = "http://patient-service/api/patients/{id}";
        return restTemplate.getForObject(url, Patient.class, patientId);
    }

    /**
     * Enrichit un rendez-vous avec les informations détaillées du patient.
     */
    private RendezVous enrichRendezVous(RendezVous rdv) {
        if (rdv != null && rdv.getPatientId() != null) {
            Patient patient = fetchPatient(rdv.getPatientId());
            rdv.setPatient(patient);
        }
        return rdv;
    }

    @Override
    public List<RendezVous> getAllRendezVous() {
        List<RendezVous> list = rendezVousRepository.findAll();
        // Pour chaque rendez-vous, on complète les infos du patient via Eureka
        list.forEach(this::enrichRendezVous);
        return list;
    }

    @Override
    public Optional<RendezVous> getRendezVousById(Long id) {
        Optional<RendezVous> optionalRdv = rendezVousRepository.findById(id);
        optionalRdv.ifPresent(this::enrichRendezVous);
        return optionalRdv;
    }

    @Override
    public RendezVous createRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous updateRendezVous(Long id, RendezVous rendezVousDetails) {
        RendezVous existingRDV = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RendezVous not found with id: " + id));

        existingRDV.setPatientId(rendezVousDetails.getPatientId());
        existingRDV.setDateHeure(rendezVousDetails.getDateHeure());
        existingRDV.setDescription(rendezVousDetails.getDescription());

        return rendezVousRepository.save(existingRDV);
    }

    @Override
    public void deleteRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }
}
