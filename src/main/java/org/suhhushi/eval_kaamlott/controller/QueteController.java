package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suhhushi.eval_kaamlott.dto.ParticipantDto;
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
}
