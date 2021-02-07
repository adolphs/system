package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.Organ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrganDAO继承基类
 */
@Mapper
public interface OrganDAO extends BaseMapper<Organ> {
    List<Organ> getOrganList(@Param("organ") Organ organ);

    int synchronizeOrgan(@Param("organList")List<Organ> organList);

    int deleteAll();
}