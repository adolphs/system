package com.wyait.manage.entity;

import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.Situation;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/7 09:21
 */
public class ComboDetailsVo {
    private String comboName;
    private String type;
    private List<Dooo> dooos;
    private List<Department> departments;
    private List<SituationVO> situations;

    public ComboDetailsVo() {
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public List<Dooo> getDooos() {
        return dooos;
    }

    public void setDooos(List<Dooo> dooos) {
        this.dooos = dooos;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<SituationVO> getSituations() {
        return situations;
    }

    public void setSituations(List<SituationVO> situations) {
        this.situations = situations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ComboDetailsVo(String comboName, String type, List<Dooo> dooos, List<Department> departments, List<SituationVO> situations) {
        this.comboName = comboName;
        this.type = type;
        this.dooos = dooos;
        this.departments = departments;
        this.situations = situations;
    }
}
