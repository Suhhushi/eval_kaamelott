package org.suhhushi.eval_kaamlott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ChevalierPerformanceDto {
    private Long idChevalier;
    private int totalQuetesTerminees;
    private int totalChefsExpedition;
    private double tauxSucces; // entre 0 et 1
    private String commentaireRoiPlusFrequent;

}
