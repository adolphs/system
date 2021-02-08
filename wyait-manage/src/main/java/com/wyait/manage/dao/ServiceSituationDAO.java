package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceSituation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ServiceSituationDAO继承基类
 */
@Repository
public interface ServiceSituationDAO extends MyBatisBaseDao<ServiceSituation, String> {
    List<Map<String,Object>> getSituationList(@Param("situation")ServiceSituation situation);

    List<ServiceSituation> selectBydoooIdAndDescribe(@Param("situation") ServiceSituation situation);

    int updateBySituationId(ServiceSituation serviceSituation);

    List<ServiceSituation> selectBydoooIdAndDescribeTwo(@Param("situation") ServiceSituation serviceSituation);

    ServiceSituation getSituationById(String situationId);

    List<ServiceSituation> querySituationByPiD(String pid);

    int delByPid(String situationId);

}