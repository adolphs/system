package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.Combo;
import com.wyait.manage.pojo.SituationDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 16:03
 */
public interface SituationDetailsMapper extends BaseMapper<SituationDetails> {
    List<SituationDetails> getSituationDetailsBySituationId(Integer situationId);

//    int insert(SituationDetails situationDetails);

    int delSituationDetail(Integer situationDetailsId);

    List<SituationDetails> getsituationDetailsByNameAndId(@Param("detailsDescribe") String detailsDescribe,@Param("situationDetailsId") Integer situationDetailsId,@Param("situationId") Integer situationId);

    int updateSituationDetails(SituationDetails situationDetails);

    List<SituationDetails> getsituationDetailsByNameAndId2(@Param("detailsDescribe")String detailsDescribe,@Param("situationId") Integer situationId);

    int delSituationDetailAndData(Integer situationDetailsId);

    int delSituationByPid(Integer situationDetailsId);

    Combo selectComboIdBySituationDetailsId(Integer situationDetailsId);
}
