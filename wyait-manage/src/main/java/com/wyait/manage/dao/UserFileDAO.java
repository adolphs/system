package com.wyait.manage.dao;

import com.wyait.manage.pojo.UserFile;
import org.springframework.stereotype.Repository;

/**
 * UserFileDAO继承基类
 */
@Repository
public interface UserFileDAO extends MyBatisBaseDao<UserFile, String> {
}