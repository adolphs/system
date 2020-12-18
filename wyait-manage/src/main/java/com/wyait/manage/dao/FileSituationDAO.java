package com.wyait.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * FileSituationDAO继承基类
 */
@Repository
public interface FileSituationDAO {
    int insert(@Param("comboId") Integer comboId,@Param("fileId") String fileId,@Param("situationDetailsId") Integer situationDetailsId);

    int delete(@Param("fileId") Long fileId,@Param("situationDetailsId") Integer situationDetailsId);
}