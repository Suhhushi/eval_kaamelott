package org.suhhushi.eval_kaamlott.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Quete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomQuete;
    private String description;
    private String difficulte;
    private LocalDate dateAssignation;
    private LocalDate dateEcheance;
}