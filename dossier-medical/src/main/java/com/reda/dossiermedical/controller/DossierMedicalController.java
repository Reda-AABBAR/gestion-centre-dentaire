package com.reda.dossiermedical.controller;

import com.reda.dossiermedical.model.DossierMedical;
import com.reda.dossiermedical.service.IDossierMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossier-medical")
@RequiredArgsConstructor
public class DossierMedicalController {

    private final IDossierMedicalService dossierMedicalService;

    @GetMapping
    public ResponseEntity<List<DossierMedical>> getAllDossiers() {
        return ResponseEntity.ok(dossierMedicalService.getAllDossiers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierById(@PathVariable Long id) {
        return dossierMedicalService.getDossierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DossierMedical> createDossier(@RequestBody DossierMedical dossier) {
        DossierMedical created = dossierMedicalService.createDossier(dossier);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> updateDossier(@PathVariable Long id, @RequestBody DossierMedical dossier) {
        try {
            DossierMedical updated = dossierMedicalService.updateDossier(id, dossier);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierMedicalService.deleteDossier(id);
        return ResponseEntity.noContent().build();
    }
}
