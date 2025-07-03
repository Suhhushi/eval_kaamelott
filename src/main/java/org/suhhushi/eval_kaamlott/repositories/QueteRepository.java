package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;

public interface QueteRepository extends JpaRepository<Quete, Long> {
    List<Quete> findByDifficulte(Quete.Difficulte difficulte);
}
