package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.FieldSetting;
import com.wyait.manage.service.db1.FieldSettingService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lp
 * @version 1.0
 * @date 2020/10/21 0021 10:04
 */
@Controller
@RequestMapping("/fieldsettings")
public class FieldSettingsController {

    @Autowired
    private FieldSettingService fieldSettingService;


    /**
     * 分页查询支持筛选加分页
     * @param page
     * @param limit
     * @param fieldSetting
     * @return
     */
    @PostMapping("/getDedepartmentList")
    @ResponseBody
    public PageDataResult getDedepartmentList(Integer page, Integer limit, FieldSetting fieldSetting){
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取部门管理列表
            pdr = (PageDataResult) fieldSettingService.getFieldSetLists("电子", page, limit);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdr;
    }

}
