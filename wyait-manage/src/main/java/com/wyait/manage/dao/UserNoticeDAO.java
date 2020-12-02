package com.wyait.manage.dao;

import com.wyait.manage.pojo.UserNotice;
import org.springframework.stereotype.Repository;

/**
 * UserNoticeDAO继承基类
 */
@Repository
public interface UserNoticeDAO extends MyBatisBaseDao<UserNotice, UserNotice> {
}