package com.wyait.manage.dao;

import com.wyait.manage.pojo.Progress;
import org.springframework.stereotype.Repository;

/**
 * ProgressDAO继承基类
 */
@Repository
public interface ProgressDAO extends MyBatisBaseDao<Progress, Progress> {
}