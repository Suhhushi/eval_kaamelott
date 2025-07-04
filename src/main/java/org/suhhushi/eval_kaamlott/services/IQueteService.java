package org.suhhushi.eval_kaamlott.services;

import org.suhhushi.eval_kaamlott.entities.Quete;

import java.util.List;

public interface IQueteService {
    List<Quete> getQuetesDifficulteAberrantePasCommenceOuEnCours();
    List<Quete> getQuetesLesPlusLongues(int limit);
}
