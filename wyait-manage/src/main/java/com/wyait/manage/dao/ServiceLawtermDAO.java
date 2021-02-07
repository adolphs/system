package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceLawterm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceLawtermDAO继承基类
 */
@Repository
public interface ServiceLawtermDAO extends MyBatisBaseDao<ServiceLawterm, ServiceLawterm> {
    int insertList(@Param("serviceLawterms") List<ServiceLawterm> serviceLawterms);
}