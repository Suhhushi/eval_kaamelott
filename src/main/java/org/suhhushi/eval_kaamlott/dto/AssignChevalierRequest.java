package org.suhhushi.eval_kaamlott.dto;

import lombok.Getter;
import lombok.Setter;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;

@Getter
@Setter
public class AssignChevalierRequest {
    private Long idChevalier;
    private ParticipationQuete.Role role;
    private ParticipationQuete.StatutParticipation statutParticipation;
}

