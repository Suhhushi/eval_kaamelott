package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhhushi.eval_kaamlott.dto.AssignChevalierRequest;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
import org.suhhushi.eval_kaamlott.entities.ParticipationQuete;
import org.suhhushi.eval_kaamlott.services.IParticipationQueteService;

import java.util.List;

@RestController
@RequestMapping("/quetes")
public class QueteController {

    @Autowired
    private IParticipationQueteService queteService;

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDto>> getParticipants(@PathVariable Long id) {
        List<ParticipantDto> participants = queteService.getParticipantsByQueteId(id);
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/{idQuete}/assigner-chevalier")
    public ResponseEntity<?> assignerChevalier(@PathVariable Long idQuete,
                                               @RequestBody AssignChevalierRequest request) {
        try {
            ParticipationQuete participation = queteService.assignerChevalierAQuete(idQuete, request);
            return ResponseEntity.ok(participation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
