package com.wyait.manage.pojo;

import java.io.Serializable;

/**
 * @author 
 * 事项库  材料和情形绑定
 */
public class ServiceMaterialsSituation implements Serializable {
    /**
     * 材料id
     */
    private String materialsId;

    /**
     * 情形id
     */
    private String situationDetailsId;

    private static final long serialVersionUID = 1L;

    public String getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(String materialsId) {
        this.materialsId = materialsId;
    }

    public String getSituationDetailsId() {
        return situationDetailsId;
    }

    public void setSituationDetailsId(String situationDetailsId) {
        this.situationDetailsId = situationDetailsId;
    }

    public ServiceMaterialsSituation(String materialsId, String situationDetailsId) {
        this.materialsId = materialsId;
        this.situationDetailsId = situationDetailsId;
    }

    public ServiceMaterialsSituation() {
    }

    @Override
    public String toString() {
        return "ServiceMaterialsSituation{" +
                "materialsId='" + materialsId + '\'' +
                ", situationDetailsId='" + situationDetailsId + '\'' +
                '}';
    }
}