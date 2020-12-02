package com.wyait.manage.dao;

import com.wyait.manage.pojo.UploadFile;
import org.springframework.stereotype.Repository;

/**
 * UploadFileDAO继承基类
 */
@Repository
public interface UploadFileDAO extends MyBatisBaseDao<UploadFile, String> {
}