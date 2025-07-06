package org.suhhushi.eval_kaamlott.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.dto.RapportActiviteMensuelDTO;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.repositories.ParticipationQueteRepository;
import org.suhhushi.eval_kaamlott.repositories.QueteRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService implements IStatsService {

    private final QueteRepository queteRepository;
    private final ParticipationQueteRepository participationRepository;

    public RapportActiviteMensuelDTO genererRapportMensuel(int mois, int annee) {
        LocalDate start = LocalDate.of(annee, mois, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        long nbQuetesNouvelles = queteRepository.countByDateAssignationBetween(start, end);
        long nbQuetesTerminees = participationRepository.countDistinctByStatutParticipationAndQuete_DateEcheanceBetween(
                ParticipationQuete.StatutParticipation.TERMINEE, start, end
        );
        long nbChevaliersImpliques = participationRepository.countDistinctChevalierByQuete_DateAssignationBetween(start, end);

        RapportActiviteMensuelDTO.QueteEchecDTO queteEchec = null;

        List<Object[]> echecStats = participationRepository.findEchecsLamentablesParQuete(start, end);
        if (!echecStats.isEmpty()) {
            Object[] top = echecStats.get(0);
            Quete quete = (Quete) top[0];

            List<String> nomsChevaliers = participationRepository
                    .findByQueteAndStatutParticipation(quete, ParticipationQuete.StatutParticipation.ECHOUEE_LAMENTABLEMENT)
                    .stream()
                    .map(p -> p.getChevalier().getNom())
                    .collect(Collectors.toList());

            queteEchec = RapportActiviteMensuelDTO.QueteEchecDTO.builder()
                    .nom(quete.getNomQuete())
                    .chevaliers(nomsChevaliers)
                    .build();
        }

        return RapportActiviteMensuelDTO.builder()
                .nbQuetesNouvelles(nbQuetesNouvelles)
                .nbQuetesTerminees(nbQuetesTerminees)
                .nbChevaliersImpliques(nbChevaliersImpliques)
                .queteEchecLamentable(queteEchec)
                .build();
    }
}
