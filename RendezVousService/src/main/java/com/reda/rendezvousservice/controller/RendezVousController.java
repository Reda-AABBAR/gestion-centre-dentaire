package com.reda.rendezvousservice.controller;

import com.reda.rendezvousservice.models.RendezVous;
import com.reda.rendezvousservice.services.IRendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
public class RendezVousController {

    private final IRendezVousService rendezVousService;

    // Récupérer la liste de tous les rendez-vous
    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        return ResponseEntity.ok(rendezVousService.getAllRendezVous());
    }

    // Récupérer un rendez-vous par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        return rendezVousService.getRendezVousById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau rendez-vous
    @PostMapping
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous createdRDV = rendezVousService.createRendezVous(rendezVous);
        return new ResponseEntity<>(createdRDV, HttpStatus.CREATED);
    }

    // Mettre à jour un rendez-vous existant
    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        try {
            RendezVous updatedRDV = rendezVousService.updateRendezVous(id, rendezVous);
            return ResponseEntity.ok(updatedRDV);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un rendez-vous
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}
