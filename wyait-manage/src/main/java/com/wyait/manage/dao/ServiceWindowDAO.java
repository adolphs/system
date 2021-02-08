package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceWindow;
import org.springframework.stereotype.Repository;

/**
 * ServiceWindowDAO继承基类
 */
@Repository
public interface ServiceWindowDAO extends MyBatisBaseDao<ServiceWindow, ServiceWindow> {

    ServiceWindow queryWindowByCarryOutCode(String carryOutCode);

}