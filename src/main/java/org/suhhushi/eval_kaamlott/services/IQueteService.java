package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.dto.QuetePeriodeDto;
import org.suhhushi.eval_kaamlott.entities.Quete;

import java.time.LocalDate;
import java.util.List;

public interface IQueteService {
    List<Quete> getQuetesDifficulteAberrantePasCommenceOuEnCours();
    List<Quete> getQuetesLesPlusLongues(int limit);
    List<QuetePeriodeDto> getQuetesParPeriode(LocalDate dateDebut, LocalDate dateFin);
}
