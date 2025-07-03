package org.suhhushi.eval_kaamlott.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {
    private String nomChevalier;
    private String titre;
    private String role;
    private String statutParticipation;

    public ParticipantDto(String nomChevalier, String titre, String role, String statutParticipation) {
        this.nomChevalier = nomChevalier;
        this.titre = titre;
        this.role = role;
        this.statutParticipation = statutParticipation;
    }
}

