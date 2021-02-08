package com.wyait.manage.service.update;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.ServiceApplicationMaterialsDAO;
import com.wyait.manage.dao.ServiceMaterialsSituationDAO;
import com.wyait.manage.dao.ServiceSituationDAO;
import com.wyait.manage.dao.ServiceSituationDetailsDAO;
import com.wyait.manage.pojo.ServiceItem;
import com.wyait.manage.pojo.ServiceMaterialsSituation;
import com.wyait.manage.pojo.ServiceSituation;
import com.wyait.manage.pojo.ServiceSituationDetails;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.IdWorkerUtils;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 16:48
 */
@Service
public class ServiceSituationServiceImpl implements ServiceSituationService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSituationServiceImpl.class);

    @Autowired
    ServiceSituationDAO serviceSituationDAO;
    @Autowired
    ServiceSituationDetailsDAO serviceSituationDetailsDAO;
    @Autowired
    ServiceApplicationMaterialsDAO serviceApplicationMaterialsDAO;
    @Autowired
    ServiceMaterialsSituationDAO serviceMaterialsSituationDAO;

    @Override
    public PageDataResult getSituationList(ServiceSituation situation, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Map<String,Object>> itemList = serviceSituationDAO.getSituationList(situation);
        // 获取分页查询后的数据
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(itemList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(itemList);
        return pdr;
    }

    @Override
    public ResponseResult addSituation(ServiceSituation situation) {
        ResponseResult result = new ResponseResult();
        if (situation.getSituationId() != null && situation.getSituationId() != ""){
            List<ServiceSituation> serviceSituations = serviceSituationDAO.selectBydoooIdAndDescribeTwo(situation);
            if (serviceSituations.size() != 0){
                result.setData("已存在相同名称的情形,请重新输入");
                result.setCode(204);
                return result;
            }
            //修改
            situation.setUpdateTime(new Date());
            serviceSituationDAO.updateBySituationId(situation);
        }else{
            List<ServiceSituation> serviceSituations = serviceSituationDAO.selectBydoooIdAndDescribe(situation);
            if (serviceSituations.size() != 0){
                result.setData("已存在相同名称的情形,请重新输入");
                result.setCode(204);
                return result;
            }
            situation.setSituationId(IdWorkerUtils.getInstance().createUUID());
            situation.setNewTime(new Date());
            serviceSituationDAO.insert(situation);
        }
        result.setCode(200);
        result.setData("情形操作成功");
        return result;
    }

    @Override
    public ResponseResult delSituation(String situationId) {
        ResponseResult result = new ResponseResult();
        serviceSituationDAO.deleteByPrimaryKey(situationId);
        result.setCode(200);
        result.setData("情形操作成功");
        return result;
    }

    @Override
    public ServiceSituation getSituationById(String situationId) {
        return serviceSituationDAO.getSituationById(situationId);
    }

    @Override
    public List<ServiceSituationDetails> getSituationDetailsBySituationId(String situationId) {
        return serviceSituationDetailsDAO.getSituationDetailsBySituationId(situationId);
    }

    @Override
    public ResponseResult addSituationDetails(ServiceSituationDetails serviceSituationDetails) {
        ResponseResult result = new ResponseResult();
        if (serviceSituationDetails.getSituationDetailsId() != null && serviceSituationDetails.getSituationDetailsId() != ""){
            List<ServiceSituationDetails> serviceSituations = serviceSituationDetailsDAO.selectBydoooIdAndDescribeTwo(serviceSituationDetails);
            if (serviceSituations.size() != 0){
                result.setData("已存在相同名称的情形,请重新输入");
                result.setCode(204);
                return result;
            }
            //修改
            serviceSituationDetails.setUpdateTime(new Date());
            serviceSituationDetailsDAO.updateBySituationDetailsId(serviceSituationDetails);
        }else{
            List<ServiceSituationDetails> serviceSituations = serviceSituationDetailsDAO.selectBydoooIdAndDescribe(serviceSituationDetails);
            if (serviceSituations.size() != 0){
                result.setData("已存在相同名称的情形,请重新输入");
                result.setCode(204);
                return result;
            }
            serviceSituationDetails.setSituationDetailsId(IdWorkerUtils.getInstance().createUUID());
            serviceSituationDetails.setNewTime(new Date());
            serviceSituationDetailsDAO.insert(serviceSituationDetails);
        }
        result.setCode(200);
        result.setData("情形操作成功");
        return result;
    }

    @Override
    @Transactional
    public ResponseResult delSituationDetails(String situationId) {
        ResponseResult result = new ResponseResult();
        serviceSituationDetailsDAO.deleteByPrimaryKey(situationId);
        serviceSituationDAO.delByPid(situationId);
        result.setCode(200);
        result.setData("情形操作成功");
        return result;
    }

    @Override
    public ResponseResult querySituationByPiD(String pid) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(serviceSituationDAO.querySituationByPiD(pid));
        return result;
    }

    @Override
    public ResponseResult queryMaterialsList(String carryOutCode) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(serviceApplicationMaterialsDAO.queryMaterialsByCarryOutCode(carryOutCode));
        return result;
    }

    @Override
    public ResponseResult addMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation) {
        ResponseResult result = new ResponseResult();
        List<ServiceMaterialsSituation>  situations = serviceMaterialsSituationDAO.selectByserviceMaterialsSituation(serviceMaterialsSituation);
        if (situations.size() != 0){
            result.setCode(202);
            result.setData("该情形已绑定相同材料，请勿重复绑定");
            return result;
        }
        serviceMaterialsSituationDAO.insert(serviceMaterialsSituation);
        result.setCode(200);
        result.setData("绑定成功");
        return result;
    }

    @Override
    public ResponseResult delMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation) {
        ResponseResult result = new ResponseResult();
        serviceMaterialsSituationDAO.delMaterialsAndSituationDetails(serviceMaterialsSituation);
        result.setCode(200);
        result.setData("解除绑定成功");
        return result;
    }

    @Override
    public ResponseResult selMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(serviceApplicationMaterialsDAO.selMaterialsBySituationDetailsId(serviceMaterialsSituation.getSituationDetailsId()));
        return result;
    }
}
