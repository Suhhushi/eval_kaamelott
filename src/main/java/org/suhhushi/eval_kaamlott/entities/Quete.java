package org.suhhushi.eval_kaamlott.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Quete {
    @Id
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Difficulte difficulte;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    // getters, setters, etc.

    public enum Difficulte {
        FACILE, MOYENNE, DIFFICILE, ABERRANTE
    }

    public enum Statut {
        PAS_ENCORE_COMMENCE, EN_COURS, TERMINEE
    }
}
