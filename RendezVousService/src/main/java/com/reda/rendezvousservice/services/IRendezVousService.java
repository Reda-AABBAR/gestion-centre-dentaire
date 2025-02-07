package com.reda.rendezvousservice.services;

import com.reda.rendezvousservice.models.RendezVous;

import java.util.List;
import java.util.Optional;

public interface IRendezVousService {
    List<RendezVous> getAllRendezVous();
    Optional<RendezVous> getRendezVousById(Long id);
    RendezVous createRendezVous(RendezVous rendezVous);
    RendezVous updateRendezVous(Long id, RendezVous rendezVous);
    void deleteRendezVous(Long id);
}
