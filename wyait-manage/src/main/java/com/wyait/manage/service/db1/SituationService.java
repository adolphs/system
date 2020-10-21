package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.Situation;
import com.wyait.manage.pojo.SituationDetails;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 16:01
 */
public interface SituationService extends IService<Situation> {
    PageDataResult getSituationList(Situation situation, Integer page, Integer limit);

    String setSituation(Situation situation);

    String delSituation(Integer situationId);

    Situation selectById(Integer situationId);

    List<SituationDetails> selectSituationDetailsBySituationId(Integer situationId);

    List<Situation> selectPidBySituationId(Integer situationId);

    String setSituationDetails(SituationDetails situationDetails);

    String delSituationDetails(Integer situationDetailsId);

    List<SituationDetails> findByDepartmentId (Integer departmentId);

    List<Situation> findBySituationId (Integer departmentId);

    /**
     * 查询事项，选择后的情形
     * @param did
     * @param sdis
     * @return
     */
    List<Map> findByDoooSituation(Integer did, String sdis);

    String setSituationTOW(Integer pid, Integer type, String situationDescribe,Integer situationId,Integer situationIdTOW);

    List<Situation> findSituationByPid(Integer situationDetailsId);

    String delSituationByPidAndSituationId(Integer situationId, Integer pid);

    List<Situation> findchildSituation(Integer pid);
}

