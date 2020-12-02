package com.wyait.manage.dao;

import com.wyait.manage.pojo.Notice;
import org.springframework.stereotype.Repository;

/**
 * NoticeDAO继承基类
 */
@Repository
public interface NoticeDAO extends MyBatisBaseDao<Notice, String> {
}