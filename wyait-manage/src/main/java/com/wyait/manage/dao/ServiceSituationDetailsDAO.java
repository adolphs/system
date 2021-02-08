package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceSituation;
import com.wyait.manage.pojo.ServiceSituationDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceSituationDetailsDAO继承基类
 */
@Repository
public interface ServiceSituationDetailsDAO {
    List<ServiceSituationDetails> getSituationDetailsBySituationId(String situationId);

    List<ServiceSituationDetails> selectBydoooIdAndDescribeTwo(@Param("situationDetails") ServiceSituationDetails situationDetails);

    int updateBySituationDetailsId(ServiceSituationDetails serviceSituationDetails);

    List<ServiceSituationDetails> selectBydoooIdAndDescribe(@Param("situationDetails") ServiceSituationDetails serviceSituationDetails);

    int insert(ServiceSituationDetails serviceSituationDetails);

    int deleteByPrimaryKey(String situationId);

}