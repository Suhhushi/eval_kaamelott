package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhhushi.eval_kaamlott.entities.Chevalier;
import org.suhhushi.eval_kaamlott.repositories.ChevalierRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chevaliers")
public class ChevalierController {

    @Autowired
    private ChevalierRepository chevalierRepository;

    @GetMapping
    public List<Chevalier> getAllChevaliers() {
        return chevalierRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Chevalier> createChevalier(@RequestBody Chevalier chevalier) {
        Chevalier saved = chevalierRepository.save(chevalier);
        return ResponseEntity.created(URI.create("/chevaliers/" + saved.getId())).body(saved);
    }
}

