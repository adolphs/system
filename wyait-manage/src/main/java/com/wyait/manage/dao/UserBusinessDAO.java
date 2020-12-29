package com.wyait.manage.dao;

import com.wyait.manage.entity.BusinessDTO;
import com.wyait.manage.entity.BusinessVO;
import com.wyait.manage.pojo.UserBusiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * UserBusinessDAO继承基类
 */
@Mapper
public interface UserBusinessDAO {
    List<BusinessVO> getBusinessList(@Param("businessDTO") BusinessDTO businessDTO);
}