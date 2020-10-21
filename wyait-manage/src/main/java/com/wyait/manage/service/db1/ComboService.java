package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.entity.ComboDetailsVo;
import com.wyait.manage.pojo.*;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 21:00
 */
public interface ComboService extends IService<Combo> {
    String setSituationTOW(Integer pid, Integer type, String situationDescribe, Integer situationId, Integer situationIdTOW);

    PageDataResult getComboList(Combo combo, Integer page, Integer limit);

    String setCombo(Combo combo);

    String delCombo(Integer comboId);

    ComboDetailsVo getComboDetailsVo(Integer id,Integer departmentId);

    String addDooo(Combo combo);

    String delComboDooo(ComboDooo comboDooo);

    String updateComboDooo(Combo combo);

    PageDataResult getComboSituationList(ComboSituation comboSituation, Integer page, Integer limit);

    String addComboSituation(Integer comboId, String situationDescribe, String detailsDescribe,Integer id);

    ComboSituation selectComboSituationById(Integer id);

    List<ComboSituation> selectPidByComboSituationId(Integer id);

    String addComboSituationV2(ComboSituation comboSituation);

    String delComboSituation(Integer id);

    List<ComboSituationDetails> selectSituationDetailsByComboSituationId(Integer id);

    String setComboSituationDetails(ComboSituationDetails comboSituationDetails);

    String delComboSituationDetails(Integer id);

    String setDataAndComboSituation(Integer dataId, Integer comboSituationId, Integer comboSituationDetailsId);

    List<ComboSituationDetails> getCmoboSituationDetails(Integer id);

    List<ComboSituation> findSituationByPid(Integer situationDetailsId);

    String delSituationByPidAndSituationId(Integer situationId, Integer pid);

    String putApprovalType(Integer comboId, Integer approvalType, String approvalText);

    List<Combo> findAllCombo();

    List<ComboSituation> findAllComboSituation(Integer comboId);

    Map<String,Object> fetchMattersNums();

    Combo getComboById(Integer id);

    ResponseResult APIComboList(String type);

    ResponseResult queryComboSituation(Integer comoId);

    ResponseResult queryComboSituationDetailsByPid(Integer comboSituationDetailsId);

    ResponseResult queryComboDataList(String comboSituationDetailsIds,Integer comboId) throws InterruptedException;

    ResponseResult getAllSituation(Integer comboId);

    /**
     * 获取套餐的流程图url
     * @param comboId 套餐id
     * @return
     */
    ResponseResult getComboUrl(Integer comboId);

    /**
     * 根据套餐ID查询所涉及到的部门
     * @param comboId
     * @return
     */
    ResponseResult getDepartmentByComboId(Integer comboId);

    /**
     * 获取热门套餐
     * @return
     */
    ResponseResult getHotCombo();

}
