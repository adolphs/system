package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceApplicationMaterials;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceApplicationMaterialsDAO继承基类
 */
@Repository
public interface ServiceApplicationMaterialsDAO extends MyBatisBaseDao<ServiceApplicationMaterials, String> {
    int insertList(@Param("applicationMaterialsList") List<ServiceApplicationMaterials> applicationMaterialsList);

    List<ServiceApplicationMaterials> queryMaterialsByCarryOutCode(String carryOutCode);

    List<ServiceApplicationMaterials> selMaterialsBySituationDetailsId(String situationDetailsId);
}