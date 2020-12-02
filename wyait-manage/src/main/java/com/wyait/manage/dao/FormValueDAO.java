package com.wyait.manage.dao;

import com.wyait.manage.pojo.FormValue;
import org.springframework.stereotype.Repository;

/**
 * FormValueDAO继承基类
 */
@Repository
public interface FormValueDAO extends MyBatisBaseDao<FormValue, String> {
}