package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.entities.Chevalier;

import java.util.List;

public interface IChevalierService {
    List<Chevalier> findByCaracteristique(String caracteristique);
}
