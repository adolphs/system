package com.wyait.manage.service.update;

import com.wyait.manage.pojo.ServiceMaterialsSituation;
import com.wyait.manage.pojo.ServiceSituation;
import com.wyait.manage.pojo.ServiceSituationDetails;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 16:48
 */
public interface ServiceSituationService {
    PageDataResult getSituationList(ServiceSituation situation, Integer page, Integer limit);

    ResponseResult addSituation(ServiceSituation situation);

    ResponseResult delSituation(String situationId);

    ServiceSituation getSituationById(String situationId);

    List<ServiceSituationDetails> getSituationDetailsBySituationId(String situationId);

    ResponseResult addSituationDetails(ServiceSituationDetails situationDetails);

    ResponseResult delSituationDetails(String situationId);

    ResponseResult querySituationByPiD(String pid);

    ResponseResult queryMaterialsList(String carryOutCode);

    ResponseResult addMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation);

    ResponseResult delMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation);

    ResponseResult selMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation);
}
