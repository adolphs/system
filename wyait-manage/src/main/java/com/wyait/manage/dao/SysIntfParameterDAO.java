package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.SysIntfParameter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     *  根据应用系统编码查询
     * @param encode
     * @return
     */
    @Select("select id,application,ip_port,account,password,secret_key,secret_val,remarks " +
            " from sys_intf_parameter " +
            " where enabled = 1 and encoded=#{encode}")
    SysIntfParameter feachSysParameter(String encode);
}