package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.utils.PageDataResult;

public interface SysIntfParameterService {

    PageDataResult getSysIntfParameterList(SysIntfParameter sysIntfParameter, Integer page, Integer limit);

    String addSysIntfParameter(SysIntfParameter sysIntfParameter, User existUser);
}
