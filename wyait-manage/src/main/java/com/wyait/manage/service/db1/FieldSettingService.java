package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.FieldSetting;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @author lp
 * @version 1.0
 * @date 2020/10/21 0021 10:09
 */
public interface FieldSettingService extends IService<FieldSetting> {

    PageDataResult getFieldSetLists(String platform, Integer page, Integer limit);
}
