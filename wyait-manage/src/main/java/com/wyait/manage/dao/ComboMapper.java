package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.entity.ComboSituationVO;
import com.wyait.manage.entity.ComboVo;
import com.wyait.manage.entity.SituationVO;
import com.wyait.manage.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:06
 */
public interface ComboMapper extends BaseMapper<Combo> {

    List<ComboVo> getCombos(@Param("combo") Combo combo);

    List<ComboDooo> getDoooCount(Integer doooId);

    int getDepartmentCount(Integer comboId);

//    int insert(@Param("combo")Combo combo);

    Combo getComboByTime();

    int insertComboDooo(@Param("comboDooo") ComboDooo comboDooo);

    int delComboDooo(Integer comboId);

    int delCombo(Integer comboId);

    Combo getComboById(Integer id);

    List<Dooo> getDoooByComboId(@Param("id") Integer id,@Param("departmentId") Integer departmentId);

    List<Situation> getSituaionVObyDoooId(int[] ids);

    List<Department> getDepartmentByDoooId(int[] ids);


    Map<String,Integer> getComboDoooByComboId(@Param("id") Integer id, @Param("doooId")Integer doooId);


    int delComboDoooByComboIdByDoooId(@Param("combo_id")Integer combo_id, @Param("dooo_id")Integer dooo_id);

    int updateComboDooo(@Param("combo") Combo combo);

    List<SituationVO> getSituationList(int[] situationIds);

    List<ComboSituationVO> getComboSituationList(@Param("comboSituation") ComboSituation comboSituation);

    List<ComboSituationDetails> getComboSituationDetailsBySituationId(Integer id);

    int addComboSituation(@Param("comboSituation") ComboSituation comboSituation);

    ComboSituation selectComboSituation();

    int insestComboSituationDetails(@Param("comboSituationDetails") ComboSituationDetails comboSituationDetails);

    int delComboSituationDetails(Integer id);

    int updateComboSituation(@Param("situationDescribe") String situationDescribe,@Param("id") Integer id);

    List<Integer> getIdByNodesType(Integer comboId);

    List<ComboSituation> getSituationPid(@Param("situationId") Integer situationId, @Param("situationDescribe") String situationDescribe);

    ComboSituation getSituationId(@Param("situationId") Integer situationId);

    List<ComboSituation> getSituationPidTow(@Param("situationId") Integer situationId, @Param("situationDescribe")String situationDescribe, @Param("situationIdTOW")Integer situationIdTOW);

    int updateSituation(@Param("comboSituation") ComboSituation comboSituation);

    int sendBackApprovalType(@Param("comboId") Integer comboId,@Param("approvalType") Integer approvalType);

    int putApprovalType(@Param("comboId") Integer doooId,@Param("approvalType") Integer approvalType,@Param("approvalText") String approvalText);

    int passApprovalType(@Param("comboId") Integer comboId, @Param("approvalType") int approvalType,@Param("approvalText") String approvalText);

    List<Combo> findAllCombo();

    /**
     * 查询一件事套餐的所有情形
     * @param comboId
     * @return
     */
    List<ComboSituation> findAllComboSituation(Integer comboId);

    Map<String,Object> fetchMattersNums();

    List<Combo> APIComboList();

    int countCombo();

    int countDoooByCombo();

    List<ComboSituation> querySituationByComboId(Integer comboId);

    List<Department> getDepartmentByComboId(@Param("comboId") Integer comboId);

    List<Combo> getComboByIds();
}
