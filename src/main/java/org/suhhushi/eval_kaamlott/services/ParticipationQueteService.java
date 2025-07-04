package org.suhhushi.eval_kaamlott.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.requests.AssignChevalierRequest;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;
import org.suhhushi.eval_kaamlott.repositories.ParticipationQueteRepository;
import org.suhhushi.eval_kaamlott.repositories.QueteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipationQueteService implements IParticipationQueteService {

    @Autowired
    private ParticipationQueteRepository participationQueteRepository;

    @Autowired
    private ChevalierRepository chevalierRepository;

    @Autowired
    private QueteRepository queteRepository;

    @Override
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

    @Override
    public ParticipationQuete assignerChevalierAQuete(Long idQuete, AssignChevalierRequest request) {
        Chevalier chevalier = chevalierRepository.findById(request.getIdChevalier())
                .orElseThrow(() -> new RuntimeException("Chevalier non trouvé"));

        Quete quete = queteRepository.findById(idQuete)
                .orElseThrow(() -> new RuntimeException("Quête non trouvée"));

        // Vérifier si le chevalier est déjà assigné à la quête
        boolean exists = participationQueteRepository.existsByChevalierAndQuete(chevalier, quete);
        if (exists) {
            throw new RuntimeException("Ce chevalier est déjà assigné à cette quête");
        }

        ParticipationQuete participation = new ParticipationQuete();
        participation.setChevalier(chevalier);
        participation.setQuete(quete);
        participation.setRole(request.getRole());
        participation.setStatutParticipation(request.getStatutParticipation());

        return participationQueteRepository.save(participation);
    }

    @Override
    public List<Quete> getQuetesEnCoursByChevalierId(Long chevalierId) {
        List<ParticipationQuete> participations = participationQueteRepository.findByChevalier_IdAndStatutParticipation(
                chevalierId,
                ParticipationQuete.StatutParticipation.EN_COURS
        );
        return participations.stream()
                .map(ParticipationQuete::getQuete)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean retirerChevalierDeQuete(Long idChevalier, Long idQuete) {
        Optional<ParticipationQuete> participationOpt = participationQueteRepository
                .findByChevalier_IdAndQuete_Id(idChevalier, idQuete);

        if (participationOpt.isEmpty()) {
            return false; // pas trouvé
        }

        participationQueteRepository.delete(participationOpt.get());
        return true;
    }

    public List<Quete> getQuetesAvecEffectifManquant(long minChevaliers) {
        return participationQueteRepository.findQuetesAvecEffectifInferieur(minChevaliers);
    }

}
