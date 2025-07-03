package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;

import java.util.List;

public interface ParticipationQueteRepository extends JpaRepository<ParticipationQuete, Long> {
    List<ParticipationQuete> findByQuete_Id(Long queteId);
}
