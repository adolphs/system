package com.wyait.manage.service.update;

import com.wyait.manage.pojo.Organ;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/5 16:18
 */

public interface OrganService {
    PageDataResult getOrganList(Organ organ, Integer page, Integer limit);

    ResponseResult synchronizeOrgan() throws Exception;
}
