package com.wyait.manage.entity;

import com.wyait.manage.pojo.Situation;
import com.wyait.manage.pojo.SituationDetails;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/8 10:53
 */
public class DoooSituationVOTOW {
    private Situation situation;
    private List<SituationDetails> situationDetailsList;

    public DoooSituationVOTOW(Situation situation, List<SituationDetails> situationDetailsList) {
        this.situation = situation;
        this.situationDetailsList = situationDetailsList;
    }

    public DoooSituationVOTOW() {
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public List<SituationDetails> getSituationDetailsList() {
        return situationDetailsList;
    }

    public void setSituationDetailsList(List<SituationDetails> situationDetailsList) {
        this.situationDetailsList = situationDetailsList;
    }
}
