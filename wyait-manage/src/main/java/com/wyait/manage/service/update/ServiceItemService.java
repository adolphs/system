package com.wyait.manage.service.update;

import com.wyait.manage.pojo.ServiceItem;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 10:23
 */
public interface ServiceItemService {
    PageDataResult getItemList(ServiceItem item, Integer page, Integer limit);

    ResponseResult synchronizeItem(String carryOutCode);
}
