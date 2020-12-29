package com.wyait.manage.service.web2;

import com.wyait.manage.entity.BusinessDTO;
import com.wyait.manage.utils.PageDataResult;

public interface BusinessService {
    PageDataResult getBusinessList(BusinessDTO businessDTO, Integer page, Integer limit);
}
