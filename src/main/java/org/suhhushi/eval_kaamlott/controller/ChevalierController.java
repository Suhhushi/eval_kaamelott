package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;
import org.suhhushi.eval_kaamlott.services.IChevalierService;
import org.suhhushi.eval_kaamlott.services.IParticipationQueteService;
import org.suhhushi.eval_kaamlott.services.ParticipationQueteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chevaliers")
public class ChevalierController {

    @Autowired
    private ChevalierRepository chevalierRepository;

    @Autowired
    private IParticipationQueteService participationQueteService;

    @GetMapping
    public List<Chevalier> getAllChevaliers() {
        return chevalierRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Chevalier> createChevalier(@RequestBody Chevalier chevalier) {
        Chevalier saved = chevalierRepository.save(chevalier);
        return ResponseEntity.created(URI.create("/chevaliers/" + saved.getId())).body(saved);
    }

    @GetMapping("/{idChevalier}/quetes-en-cours")
    public List<Quete> getQuetesEnCours(@PathVariable Long idChevalier) {
        return participationQueteService.getQuetesEnCoursByChevalierId(idChevalier);
    }

    @GetMapping("/{idChevalier}/retirer-quete/{idQuete}")
    public ResponseEntity<String> retirerQuete(
            @PathVariable Long idChevalier,
            @PathVariable Long idQuete) {

        boolean success = participationQueteService.retirerChevalierDeQuete(idChevalier, idQuete);
        if (success) {
            return ResponseEntity.ok("Chevalier retiré de la quête avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Participation chevalier-quête non trouvée.");
        }
    }
}

