package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;
import java.util.Optional;

public interface ParticipationQueteRepository extends JpaRepository<ParticipationQuete, Long> {
    List<ParticipationQuete> findByQuete_Id(Long queteId);
    boolean existsByChevalierAndQuete(Chevalier chevalier, Quete quete);
    List<ParticipationQuete> findByChevalier_IdAndStatutParticipation(Long chevalierId, ParticipationQuete.StatutParticipation statut);
    Optional<ParticipationQuete> findByChevalier_IdAndQuete_Id(Long chevalierId, Long queteId);

}
