package org.suhhushi.eval_kaamlott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhhushi.eval_kaamlott.entities.Chevalier;

import java.util.List;

public interface ChevalierRepository extends JpaRepository<Chevalier, Long> {
    List<Chevalier> findByCaracteristiquePrincipale(String caracteristiquePrincipale);
}

