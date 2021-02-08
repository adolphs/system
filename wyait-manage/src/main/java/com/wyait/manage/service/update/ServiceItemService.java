package com.wyait.manage.service.update;

import com.wyait.manage.pojo.ServiceApplicationMaterials;
import com.wyait.manage.pojo.ServiceItem;
import com.wyait.manage.pojo.ServiceLegalBasis;
import com.wyait.manage.pojo.ServiceWindow;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 10:23
 */
public interface ServiceItemService {
    PageDataResult getItemList(ServiceItem item, Integer page, Integer limit);

    ResponseResult synchronizeItem(String carryOutCode);

    ServiceItem queryItemByCarryOutCode(String carryOutCode);

    ServiceWindow queryWindowByCarryOutCode(String carryOutCode);

    List<ServiceLegalBasis> queryLegalBasisByCarryOutCode(String carryOutCode);

    List<ServiceApplicationMaterials> queryMaterialsByCarryOutCode(String carryOutCode);

    ResponseResult selectLawterm(String basisCode);

    ResponseResult updateApprovalType(ServiceItem serviceItem);
}
