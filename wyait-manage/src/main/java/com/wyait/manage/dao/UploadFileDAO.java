package com.wyait.manage.dao;

import com.wyait.manage.pojo.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UploadFileDAO继承基类
 */
@Mapper
public interface UploadFileDAO{

    List<UploadFile> getUploadFileList(@Param("uploadFile") UploadFile uploadFile);

    int insert(UploadFile uploadFile);

    int update(UploadFile uploadFile);

    int delete(String fileId);
}