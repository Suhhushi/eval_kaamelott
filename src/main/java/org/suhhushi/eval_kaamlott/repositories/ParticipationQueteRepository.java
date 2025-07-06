package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ParticipationQueteRepository extends JpaRepository<ParticipationQuete, Long> {
    List<ParticipationQuete> findByQuete_Id(Long queteId);
    boolean existsByChevalierAndQuete(Chevalier chevalier, Quete quete);
    List<ParticipationQuete> findByChevalier_IdAndStatutParticipation(Long chevalierId, ParticipationQuete.StatutParticipation statut);
    Optional<ParticipationQuete> findByChevalier_IdAndQuete_Id(Long chevalierId, Long queteId);

    @Query("SELECT pq.quete FROM ParticipationQuete pq GROUP BY pq.quete HAVING COUNT(pq.chevalier) < :min")
    List<Quete> findQuetesAvecEffectifInferieur(@Param("min") long min);

    int countByChevalierIdAndStatutParticipation(Long idChevalier, ParticipationQuete.StatutParticipation statut);

    int countByChevalierIdAndRole(Long idChevalier, ParticipationQuete.Role role);

    @Query("SELECT p.commentaireRoi FROM ParticipationQuete p WHERE p.chevalier.id = :idChevalier AND p.commentaireRoi IS NOT NULL GROUP BY p.commentaireRoi ORDER BY COUNT(p.commentaireRoi) DESC")
    List<String> findCommentairesParFrequenceDesc(@Param("idChevalier") Long idChevalier);

    default String findCommentaireRoiLePlusFrequent(Long idChevalier) {
        List<String> commentaires = findCommentairesParFrequenceDesc(idChevalier);
        return commentaires.isEmpty() ? null : commentaires.get(0);
    }
    long countDistinctByStatutParticipationAndQuete_DateEcheanceBetween(
            ParticipationQuete.StatutParticipation statut, LocalDate debut, LocalDate fin
    );

    long countDistinctChevalierByQuete_DateAssignationBetween(LocalDate debut, LocalDate fin);

    List<ParticipationQuete> findByQueteAndStatutParticipation(
            Quete quete, ParticipationQuete.StatutParticipation statut
    );

    @Query("""
        SELECT pq.quete, COUNT(pq)
        FROM ParticipationQuete pq
        WHERE pq.statutParticipation = 'ECHOUEE_LAMENTABLEMENT'
          AND pq.quete.dateAssignation BETWEEN :debut AND :fin
        GROUP BY pq.quete
        ORDER BY COUNT(pq) DESC
        """)
    List<Object[]> findEchecsLamentablesParQuete(@Param("debut") LocalDate debut, @Param("fin") LocalDate fin);
}
