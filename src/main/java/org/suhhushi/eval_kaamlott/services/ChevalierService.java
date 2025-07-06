package org.suhhushi.eval_kaamlott.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.dto.ChevalierPerformanceDto;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;
import org.suhhushi.eval_kaamlott.repositories.ParticipationQueteRepository;

import java.util.List;

@Service
public class ChevalierService {
    @Autowired
    private ChevalierRepository chevalierRepository;

    @Autowired
    private ParticipationQueteRepository participationQueteRepository;

    public List<Chevalier> findByCaracteristique(String caracteristique) {
        return chevalierRepository.findByCaracteristiquePrincipale(caracteristique);
    }

    public ChevalierPerformanceDto calculerRapportPerformance(Long idChevalier) {
        int totalTerminees = participationQueteRepository.countByChevalierIdAndStatutParticipation(idChevalier, ParticipationQuete.StatutParticipation.TERMINEE);

        int totalChefs = participationQueteRepository.countByChevalierIdAndRole(idChevalier, ParticipationQuete.Role.CHEF_EXPEDITION);

        int totalEnCours = participationQueteRepository.countByChevalierIdAndStatutParticipation(idChevalier, ParticipationQuete.StatutParticipation.EN_COURS);

        double tauxSucces = (totalTerminees + totalEnCours) > 0 ?
                (double) totalTerminees / (totalTerminees + totalEnCours) : 0.0;

        String commentairePlusFrequent = participationQueteRepository.findCommentaireRoiLePlusFrequent(idChevalier);

        return new ChevalierPerformanceDto(
                idChevalier,
                totalTerminees,
                totalChefs,
                tauxSucces,
                commentairePlusFrequent
        );
    }
}

