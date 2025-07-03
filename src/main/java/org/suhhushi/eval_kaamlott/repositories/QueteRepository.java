package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;

public interface QueteRepository extends JpaRepository<Quete, Long> {
    List<Quete> findByDifficulteAndStatutIn(Quete.Difficulte difficulte, List<Quete.Statut> statuts);
}
