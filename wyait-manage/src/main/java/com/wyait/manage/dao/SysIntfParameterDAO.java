package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.SysIntfParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysIntfParameterDAO继承基类
 */
public interface SysIntfParameterDAO extends BaseMapper<SysIntfParameter> {

    /**
     * 查询列表
     * @param sysIntfParameter
     * @return
     */
    List<SysIntfParameter> getSysIntfParameterList(@Param("sysIntfParameter") SysIntfParameter sysIntfParameter);

    /**
     * 根据ID修改系统接口
     * @param sysIntfParameter
     * @return
     */
    int updateByPrimaryKeySelective(SysIntfParameter sysIntfParameter);
}