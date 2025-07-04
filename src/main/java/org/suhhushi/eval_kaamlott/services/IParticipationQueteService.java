package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.requests.AssignChevalierRequest;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;

public interface IParticipationQueteService {
    boolean retirerChevalierDeQuete(Long idChevalier, Long idQuete);
    List<ParticipantDto> getParticipantsByQueteId(Long queteId);
    ParticipationQuete assignerChevalierAQuete(Long idQuete, AssignChevalierRequest request);
    List<Quete> getQuetesEnCoursByChevalierId(Long chevalierId);
    List<Quete> getQuetesAvecEffectifManquant(long minChevaliers);
}
