package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;
import org.suhhushi.eval_kaamlott.services.ParticipationQueteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chevaliers")
public class ChevalierController {

    @Autowired
    private ChevalierRepository chevalierRepository;

    @Autowired
    private ParticipationQueteService participationQueteService;

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
}

