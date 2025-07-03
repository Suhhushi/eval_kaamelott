package org.suhhushi.eval_kaamlott.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Quete {
    @Id
    private Long id;

    private String nomQuete;

    private String description;

    @Enumerated(EnumType.STRING)
    private Difficulte difficulte;

    private LocalDate dateAssignation;

    private LocalDate dateEcheance;

    public enum Difficulte {
        FACILE, MOYENNE, DIFFICILE, ABERRANTE
    }
}

