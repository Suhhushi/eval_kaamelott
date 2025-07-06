package org.suhhushi.eval_kaamlott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suhhushi.eval_kaamlott.dto.RapportActiviteMensuelDTO;
import org.suhhushi.eval_kaamlott.services.IStatsService;


@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private IStatsService statsService;


    @GetMapping("/rapport-activite-mensuel")
    public ResponseEntity<RapportActiviteMensuelDTO> getRapportMensuel(
            @RequestParam int mois,
            @RequestParam int annee) {
        return ResponseEntity.ok(statsService.genererRapportMensuel(mois, annee));
    }
}
