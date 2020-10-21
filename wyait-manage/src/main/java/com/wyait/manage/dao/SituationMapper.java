package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.entity.SituationVO;
import com.wyait.manage.pojo.Situation;
import com.wyait.manage.pojo.SituationDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 16:03
 */
public interface SituationMapper extends BaseMapper<Situation> {
    List<SituationVO> getSituationList(@Param("situation") Situation situation);

    Situation getSituationByNameAndId(@Param("situationDescribe") String situationDescribe,@Param("situationId") Integer situationId);

    Situation getSituationByName(@Param("situationDescribe") String situationDescribe,@Param("pid") Integer pid);

    int updateSituation(Situation situation);

//    int insert(Situation situation);

    Situation getOrderBy();

    int delSituation(Integer situationId);

    int delSituationByGoooId(Integer doooId);

    List<Situation> getSituationById(Integer doooId);

    Situation getSituationId(Integer situationId);

    List<Situation> selectPidBySituationId(Integer situationId);

    int delDataAndSituation(Integer situationId);

    List<Situation> getSituationByIdAndNodesType(Integer doooId);

    List<SituationDetails> findByDepartmentId (Integer departmentId);

    List<Situation> findBySituationId (Integer departmentId);

    List<Map> findByDoooSituation(Integer did, String sdis);

    List<Situation> findSituationByPid(Integer situationDetailsId);

    int delSituationByPidAndSituationId(@Param("situationId") Integer situationId, @Param("pid") Integer pid);

    List<Situation> getSituationPid(@Param("situationId")Integer situationId,@Param("situationDescribe")String situationDescribe);

    List<Situation> getSituationPidTow(@Param("situationId")Integer situationId, @Param("situationDescribe")String situationDescribe,@Param("situationIdTOW") Integer situationIdTOW);

    List<Situation> findchildSituation(Integer pid);

    List<Situation> queryDoooSituationList(Integer doooId);

    List<Situation> querySituationDetailsByPid(Integer situationDetailsId);
}
