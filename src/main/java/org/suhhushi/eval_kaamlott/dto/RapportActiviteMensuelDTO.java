package org.suhhushi.eval_kaamlott.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RapportActiviteMensuelDTO {
    private long nbQuetesNouvelles;
    private long nbQuetesTerminees;
    private long nbChevaliersImpliques;
    private QueteEchecDTO queteEchecLamentable;

    @Data
    @Builder
    @AllArgsConstructor
    public static class QueteEchecDTO {
        private String nom;
        private List<String> chevaliers;
    }
}
