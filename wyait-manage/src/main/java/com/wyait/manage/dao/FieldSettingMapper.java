package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.FieldSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lp
 * @version 1.0
 * @date 2020/10/21 0021 10:10
 */
public interface FieldSettingMapper extends BaseMapper<FieldSetting> {

    List<FieldSetting> getFieldSetList(@Param("platform") String platform);
}
