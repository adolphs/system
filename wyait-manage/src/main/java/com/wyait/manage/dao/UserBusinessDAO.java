package com.wyait.manage.dao;

import com.wyait.manage.pojo.UserBusiness;
import org.springframework.stereotype.Repository;

/**
 * UserBusinessDAO继承基类
 */
@Repository
public interface UserBusinessDAO extends MyBatisBaseDao<UserBusiness, String> {
}