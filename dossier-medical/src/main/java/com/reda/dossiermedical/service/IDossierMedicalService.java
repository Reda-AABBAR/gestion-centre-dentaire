package com.reda.dossiermedical.service;

import com.reda.dossiermedical.model.DossierMedical;
import java.util.List;
import java.util.Optional;

public interface IDossierMedicalService {
    List<DossierMedical> getAllDossiers();
    Optional<DossierMedical> getDossierById(Long id);
    DossierMedical createDossier(DossierMedical dossier);
    DossierMedical updateDossier(Long id, DossierMedical dossier);
    void deleteDossier(Long id);
}
