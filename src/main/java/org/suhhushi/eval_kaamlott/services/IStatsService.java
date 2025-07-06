package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.dto.RapportActiviteMensuelDTO;

import java.util.Map;

public interface IStatsService {
    RapportActiviteMensuelDTO genererRapportMensuel(int mois, int annee);
}
