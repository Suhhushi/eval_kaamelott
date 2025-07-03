package org.suhhushi.eval_kaamlott.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.repositories.ParticipationQueteRepository;

import java.util.List;

@Service
public class ParticipationQueteService implements IParticipationQueteService {

    @Autowired
    private ParticipationQueteRepository participationQueteRepository;

    public List<ParticipantDto> getParticipantsByQueteId(Long queteId) {
        List<ParticipationQuete> participations = participationQueteRepository.findByQuete_Id(queteId);

        return participations.stream()
                .map(p -> new ParticipantDto(
                        p.getChevalier().getNom(),
                        p.getChevalier().getTitre(),
                        p.getRole().name(),
                        p.getStatutParticipation().name()
                ))
                .toList();
    }
}
