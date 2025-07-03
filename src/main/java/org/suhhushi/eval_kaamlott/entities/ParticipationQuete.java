package org.suhhushi.eval_kaamlott.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class ParticipationQuete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_chevalier")
    private Chevalier chevalier;

    @ManyToOne
    @JoinColumn(name = "id_quete")
    private Quete quete;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private StatutParticipation statutParticipation;

    private String commentaireRoi;

    public enum Role {
        CHEF_EXPEDITION, ACCOLYTE, RESERVE
    }

    public enum StatutParticipation {
        EN_COURS, TERMINEE, ECHOUEE_LAMENTABLEMENT, ABANDONNEE_PAR_FLEME
    }
}

