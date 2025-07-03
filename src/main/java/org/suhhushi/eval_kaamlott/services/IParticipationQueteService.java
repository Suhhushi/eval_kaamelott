package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.dto.AssignChevalierRequest;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;

public interface IParticipationQueteService {
    List<ParticipantDto> getParticipantsByQueteId(Long queteId);
    ParticipationQuete assignerChevalierAQuete(Long idQuete, AssignChevalierRequest request);
    List<Quete> getQuetesEnCoursByChevalierId(Long chevalierId);
}
