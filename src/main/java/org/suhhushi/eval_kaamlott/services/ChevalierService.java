package org.suhhushi.eval_kaamlott.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;

import java.util.List;

@Service
public class ChevalierService {
    @Autowired
    private ChevalierRepository chevalierRepository;

    public List<Chevalier> findByCaracteristique(String caracteristique) {
        return chevalierRepository.findByCaracteristiquePrincipale(caracteristique);
    }
}

