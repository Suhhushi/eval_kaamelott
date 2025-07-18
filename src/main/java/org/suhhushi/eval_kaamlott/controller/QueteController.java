package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhhushi.eval_kaamlott.dto.QuetePeriodeDto;
import org.suhhushi.eval_kaamlott.requests.AssignChevalierRequest;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.entities.Quete;
import org.suhhushi.eval_kaamlott.services.IParticipationQueteService;
import org.suhhushi.eval_kaamlott.services.IQueteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/quetes")
public class QueteController {

    @Autowired
    private IParticipationQueteService participationQueteService;

    @Autowired
    private IQueteService queteService;

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDto>> getParticipants(@PathVariable Long id) {
        List<ParticipantDto> participants = participationQueteService.getParticipantsByQueteId(id);
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/{idQuete}/assigner-chevalier")
    public ResponseEntity<?> assignerChevalier(@PathVariable Long idQuete,
                                               @RequestBody AssignChevalierRequest request) {
        try {
            ParticipationQuete participation = participationQueteService.assignerChevalierAQuete(idQuete, request);
            return ResponseEntity.ok(participation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/difficulte-aberrante")
    public List<Quete> getQuetesDifficulteAberrante() {
        return queteService.getQuetesDifficulteAberrantePasCommenceOuEnCours();
    }

    @GetMapping("/effectif-manquant")
    public List<Quete> getQuetesAvecEffectifManquant(@RequestParam("minChevaliers") long minChevaliers) {
        return participationQueteService.getQuetesAvecEffectifManquant(minChevaliers);
    }

    @GetMapping("/les-plus-longues")
    public List<Quete> getQuetesLesPlusLongues(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        return queteService.getQuetesLesPlusLongues(limit);
    }

    @GetMapping("/periode")
    public List<QuetePeriodeDto> getQuetesParPeriode(
            @RequestParam("date_debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("date_fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin
    ) {
        return queteService.getQuetesParPeriode(dateDebut, dateFin);
    }


}
