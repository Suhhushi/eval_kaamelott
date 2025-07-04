package org.suhhushi.eval_kaamlott.dto;

import org.suhhushi.eval_kaamlott.entities.Quete;

public record QuetePeriodeDto(
        String nomQuete,
        int nbChevaliers,
        String statut,
        long dureeJours,
        Quete.Difficulte difficulte
) {}

