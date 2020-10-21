package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.ComboSituation;
import com.wyait.manage.pojo.ComboSituationDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/7 15:31
 */
public interface ComboSiuationMapper extends BaseMapper<ComboSituation> {
    ComboSituation selectComboSituationById(Integer id);

    List<ComboSituation> selectPidByComboSituationId(Integer id);

    ComboSituation selectNameAnId(@Param("pid") Integer pid,@Param("situationDescribe") String situationDescribe);

    int addComboSituationV2(ComboSituation comboSituation);

    ComboSituation selectNameAnIdById(@Param("pid")Integer pid, @Param("situationDescribe")String situationDescribe,@Param("id") Integer id1);

    int update(ComboSituation comboSituation);

    int delComboSituation(Integer id);

    List<ComboSituationDetails> selectSituationDetailsByComboSituationId(Integer id);

    int setComboSituationDetails(ComboSituationDetails comboSituationDetails);

    ComboSituationDetails queryComboSituationDetails(@Param("detailsDescribe") String detailsDescribe,@Param("comboSituationId") Integer comboSituationId);

    ComboSituationDetails queryComboSituationDetailsById(@Param("detailsDescribe") String detailsDescribe,@Param("comboSituationId")  Integer comboSituationId, @Param("id") Integer id);

    int editComboSituationDetails(ComboSituationDetails comboSituationDetails);

    int delComboSituationDetails(Integer id);

    List<ComboSituationDetails> getCmoboSituationDetails(Integer id);

    int delComboSituationByPid(Integer id);

    List<ComboSituation> findSituationByPid(Integer situationDetailsId);

    int delSituationByPidAndSituationId(@Param("situationId") Integer situationId, @Param("pid") Integer pid);
}
