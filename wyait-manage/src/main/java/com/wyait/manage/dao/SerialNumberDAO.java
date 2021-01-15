package com.wyait.manage.dao;

import com.wyait.manage.pojo.SerialNumber;
import org.apache.ibatis.annotations.Mapper;

/**
 * SerialNumberDAO继承基类
 */
@Mapper
public interface SerialNumberDAO {

    SerialNumber selectByPrimaryKey(String serialNumberId);

    int deleteByPrimaryKey(String serialNumberId);

    int insert(SerialNumber serialNumber);

    int insertSelective(SerialNumber serialNumber);

    int updateByPrimaryKeySelective(SerialNumber serialNumber);

    int updateByPrimaryKey(SerialNumber serialNumber);

}
