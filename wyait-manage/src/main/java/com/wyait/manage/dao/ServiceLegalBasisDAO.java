package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceLegalBasis;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceLegalBasisDAO继承基类
 */
@Repository
public interface ServiceLegalBasisDAO extends MyBatisBaseDao<ServiceLegalBasis, ServiceLegalBasis> {
    int insertList(@Param("serviceLegalBases") List<ServiceLegalBasis> serviceLegalBases);

    List<ServiceLegalBasis> queryLegalBasisByCarryOutCode(String carryOutCode);
}