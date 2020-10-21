package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.LogSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:06
 */
public interface LogSystemMapper extends BaseMapper<LogSystem> {

    List<LogSystem> getLogSystemList(@Param("logSystem") LogSystem logSystem);

//    int insert(LogSystem logSystem);
}
