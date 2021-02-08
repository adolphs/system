package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceMaterialsSituation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceMaterialsSituationDAO继承基类
 */
@Repository
public interface ServiceMaterialsSituationDAO extends MyBatisBaseDao<ServiceMaterialsSituation, ServiceMaterialsSituation> {
    int delMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation);

    List<ServiceMaterialsSituation> selectByserviceMaterialsSituation(@Param("serviceMaterialsSituation") ServiceMaterialsSituation serviceMaterialsSituation);
}