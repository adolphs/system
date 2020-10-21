package com.wyait.manage.entity;

import com.wyait.manage.pojo.ComboSituation;
import com.wyait.manage.pojo.ComboSituationDetails;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/8 09:08
 */
public class ComboSituationVOTOW {
    private ComboSituation comboSituation;
    private List<ComboSituationDetails> comboSituationDetailsList;

    public ComboSituationVOTOW(ComboSituation comboSituation, List<ComboSituationDetails> comboSituationDetailsList) {
        this.comboSituation = comboSituation;
        this.comboSituationDetailsList = comboSituationDetailsList;
    }

    public ComboSituationVOTOW() {
    }

    public ComboSituation getComboSituation() {
        return comboSituation;
    }

    public void setComboSituation(ComboSituation comboSituation) {
        this.comboSituation = comboSituation;
    }

    public List<ComboSituationDetails> getComboSituationDetailsList() {
        return comboSituationDetailsList;
    }

    public void setComboSituationDetailsList(List<ComboSituationDetails> comboSituationDetailsList) {
        this.comboSituationDetailsList = comboSituationDetailsList;
    }
}
