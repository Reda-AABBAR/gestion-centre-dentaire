package com.reda.rendezvousservice.models;

import com.reda.rendezvousservice.classes.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identifiant du patient associé (on pourrait aussi appeler une API PatientService pour obtenir les détails)
    private Long patientId;

    private LocalDateTime dateHeure;

    private String description;

    @Transient
    private Patient patient;
}
