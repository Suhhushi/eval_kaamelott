package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.dto.ParticipantDto;

import java.util.List;

public interface IParticipationQueteService {
    public List<ParticipantDto> getParticipantsByQueteId(Long queteId);
}
