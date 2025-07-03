package org.suhhushi.eval_kaamlott.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Chevalier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String titre;
    private String caracteristiquePrincipale;
    private int niveauBravoure;
}
