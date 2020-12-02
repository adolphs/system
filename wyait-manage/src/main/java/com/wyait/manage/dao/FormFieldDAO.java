package com.wyait.manage.dao;

import com.wyait.manage.pojo.FormField;
import org.springframework.stereotype.Repository;

/**
 * FormFieldDAO继承基类
 */
@Repository
public interface FormFieldDAO extends MyBatisBaseDao<FormField, String> {
}