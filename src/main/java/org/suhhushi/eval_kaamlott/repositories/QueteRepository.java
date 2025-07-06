package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.enumeration.StatutQuete;

import java.time.LocalDate;
import java.util.List;

public interface QueteRepository extends JpaRepository<Quete, Long> {
    List<Quete> findByDifficulte(Quete.Difficulte difficulte);

    @Query("SELECT q FROM Quete q ORDER BY DATEDIFF(q.dateEcheance, q.dateAssignation) DESC")
    List<Quete> findQuetesOrderByDureeDesc();

    @Query("""
            SELECT q FROM Quete q 
            WHERE q.dateAssignation <= :dateFin 
              AND q.dateEcheance >= :dateDebut
            """)
    List<Quete> findByPeriodeChevauchante(LocalDate dateDebut, LocalDate dateFin);

    long countByDateAssignationBetween(LocalDate debut, LocalDate fin);
}
