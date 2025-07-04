package org.suhhushi.eval_kaamlott.services;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.dto.QuetePeriodeDto;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.enumeration.StatutQuete;
import org.suhhushi.eval_kaamlott.repositories.QueteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class QueteService implements IQueteService {

    @Autowired
    private QueteRepository queteRepository;

    @Override
    public List<Quete> getQuetesDifficulteAberrantePasCommenceOuEnCours() {
        return queteRepository.findByDifficulte(Quete.Difficulte.ABERRANTE).stream()
                .filter(q -> {
                    StatutQuete statut = calculerStatut(q);
                    return statut == StatutQuete.PAS_ENCORE_COMMENCE || statut == StatutQuete.EN_COURS;
                })
                .toList();
    }

    public List<Quete> getQuetesLesPlusLongues(int limit) {
        return queteRepository.findQuetesOrderByDureeDesc()
                .stream()
                .limit(limit)
                .toList();
    }

    public List<QuetePeriodeDto> getQuetesParPeriode(LocalDate dateDebut, LocalDate dateFin) {
        var quetes = queteRepository.findByPeriodeChevauchante(dateDebut, dateFin);
        return quetes.stream().map(q -> {
            int nbChevaliers = q.getParticipations().size(); // si mappedBy dans Quete
            String statut = calculerStatutGlobal(q);
            long duree = ChronoUnit.DAYS.between(q.getDateAssignation(), q.getDateEcheance());

            return new QuetePeriodeDto(
                    q.getNomQuete(),
                    nbChevaliers,
                    statut,
                    duree,
                    q.getDifficulte()
            );
        }).toList();
    }

    private String calculerStatutGlobal(Quete quete) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(quete.getDateAssignation())) return "À Venir";
        if (now.isAfter(quete.getDateEcheance())) return "Terminée";
        return "En Cours";
    }

    private StatutQuete calculerStatut(Quete quete) {
        LocalDate nowDate = LocalDate.now();
        LocalDate assignationDate = quete.getDateAssignation();
        LocalDate echeanceDate = quete.getDateEcheance();

        if (nowDate.isBefore(assignationDate)) {
            // On est avant la date d'assignation
            return StatutQuete.PAS_ENCORE_COMMENCE;
        } else if ((nowDate.isEqual(assignationDate) || nowDate.isAfter(assignationDate))
                && (nowDate.isBefore(echeanceDate) || nowDate.isEqual(echeanceDate))) {
            // On est entre la date d'assignation et la date d'échéance (inclus)
            return StatutQuete.EN_COURS;
        } else {
            // On est après la date d'échéance
            return StatutQuete.TERMINEE;
        }
    }

}
