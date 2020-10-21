package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.FieldSettingMapper;
import com.wyait.manage.pojo.FieldSetting;
import com.wyait.manage.service.db1.FieldSettingService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author lp
 * @version 1.0
 * @date 2020/10/21 0021 10:11
 */
@Service
public class FieldSettingServiceImpl extends ServiceImpl<FieldSettingMapper, FieldSetting> implements FieldSettingService {

    @Autowired
    private FieldSettingMapper fieldSettingMapper;

    @Override
    public PageDataResult getFieldSetLists(String platform, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<FieldSetting> doooList = fieldSettingMapper.getFieldSetList(platform);
        // 获取分页查询后的数据
        PageInfo<FieldSetting> pageInfo = new PageInfo<>(doooList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(doooList);
        return pdr;
    }
}
