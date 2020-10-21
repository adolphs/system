package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.*;
import com.wyait.manage.utils.PageDataResult;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 20:59
 */
public interface LogSystemService extends IService<LogSystem> {
    PageDataResult getLogSystemList(LogSystem logSystem, Integer page, Integer limit);

    String addLog(User existUser, Department department);

    String addLog(User existUser, Dooo dooo);

    String addLog(User existUser, Situation situation);

    String addLog(User existUser, Combo combo);

    String del(User existUser, Integer departmentId, int i);
}
