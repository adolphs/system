package com.wyait.manage.service.update;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.common.JSONUtils;
import com.wyait.manage.controller.web.ServiceItemController;
import com.wyait.manage.dao.*;
import com.wyait.manage.pojo.*;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.ProjectConstructionApprovalService;
import com.wyait.manage.utils.JsonUtil;
import com.wyait.manage.utils.PageDataResult;
import groovy.transform.ThreadInterrupt;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 10:23
 */
@Service
public class ServiceItemServiceImpl implements ServiceItemService{
    private static final Logger logger = LoggerFactory.getLogger(ServiceItemServiceImpl.class);

    @Autowired
    ServiceItemDAO serviceItemDAO;
    @Autowired
    ServiceApplicationMaterialsDAO serviceApplicationMaterialsDAO;
    @Autowired
    ServiceWindowDAO serviceWindowDAO;
    @Autowired
    ServiceLawtermDAO serviceLawtermDAO;
    @Autowired
    ServiceLegalBasisDAO serviceLegalBasisDAO;
    @Autowired
    ProjectConstructionApprovalService projectConstructionApprovalService;

    @Override
    public PageDataResult getItemList(ServiceItem item, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<ServiceItem> itemList = serviceItemDAO.getItemList(item);
        // 获取分页查询后的数据
        PageInfo<ServiceItem> pageInfo = new PageInfo<>(itemList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(itemList);
        return pdr;
    }

    @Override
    @Transactional
    public ResponseResult synchronizeItem(String carryOutCode) {
        ResponseResult result = new ResponseResult();
        List<ServiceItem> items = serviceItemDAO.getItemList2(carryOutCode);
        if (items.size() != 0){
            result.setCode(202);
            result.setData("已存在相关事项，请勿重新拉取。如果需要重新拉取，请删除原先的事项");
            return result;
        }
        //开始同步
        logger.info("开始同步事项，事项编码 = " + carryOutCode);
        String responseText = projectConstructionApprovalService.getItemList(carryOutCode,null,null);
        JSONObject jsonObject = JSONObject.fromObject(responseText);
        logger.info("---responseText----"+ jsonObject);
        if ("1".equals(jsonObject.getString("state")) && jsonObject!=null){
            JSONObject item = JSONObject.fromObject(jsonObject.getString("data"));
            //事项本身
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setAccept_time(item.getString("accept_time"));
            serviceItem.setAccept_time_sp(item.getString("accept_time_sp"));
            serviceItem.setApply_time(item.getString("apply_time"));
            serviceItem.setConditions(item.getString("conditions"));
            serviceItem.setService_code(item.getString("service_code"));
            serviceItem.setCtg_code(item.getString("ctg_code"));
            serviceItem.setEms(item.getString("ems"));
            serviceItem.setFaq(item.getString("faq"));
            serviceItem.setCarry_out_code(item.getString("carry_out_code"));
            serviceItem.setHave_certificate(item.getString("have_certificate"));
            serviceItem.setLegal_period(item.getString("legal_period"));
            serviceItem.setTheme_gr_type(item.getString("theme_gr_type"));
            serviceItem.setTheme_fr_type(item.getString("theme_fr_type"));
            serviceItem.setSuit_online(item.getString("suit_online"));
            serviceItem.setService_agent_name(item.getString("service_agent_name"));
            serviceItem.setService_agent_code(item.getString("service_agent_code"));
            serviceItem.setPromised_period_type(item.getString("promised_period_type"));
            serviceItem.setOnline_service_url(item.getString("online_service_url"));
            serviceItem.setNeed_charge(item.getString("need_charge"));
            serviceItem.setName(item.getString("name"));
            serviceItem.setLegal_period_type(item.getString("legal_period_type"));
            serviceItem.setPromised_period(item.getString("promised_period"));
            int itemInt = serviceItemDAO.insert(serviceItem);
            if (itemInt == 0){
                result.setCode(502);
                result.setData("新增事项失败，原因请联系开发人员检查");
            }
            //法律法规
            List<Map<String, Object>> legalBasis = JsonUtil.jsonToList(item.getString("legal_basis"));
            List<ServiceLegalBasis> serviceLegalBases = new ArrayList<>();
            List<ServiceLawterm> serviceLawterms = new ArrayList<>();
            for (int i = 0 ;i<legalBasis.size();i++){
               ServiceLegalBasis serviceLegalBasis = new ServiceLegalBasis();
               serviceLegalBasis.setBasis_type(legalBasis.get(i).get("basis_type")==null?null:legalBasis.get(i).get("basis_type").toString());
               serviceLegalBasis.setWh(legalBasis.get(i).get("wh")==null?null:legalBasis.get(i).get("wh").toString());
               serviceLegalBasis.setCarry_out_date(new Date(Long.parseLong(legalBasis.get(i).get("carry_out_date")==null?null:legalBasis.get(i).get("carry_out_date").toString())));
               serviceLegalBasis.setBasis_code(legalBasis.get(i).get("basis_code")==null?null:legalBasis.get(i).get("basis_code").toString());
               serviceLegalBasis.setName(legalBasis.get(i).get("name")==null?null:legalBasis.get(i).get("name").toString());
               serviceLegalBasis.setOffice(legalBasis.get(i).get("office")==null?null:legalBasis.get(i).get("office").toString());
               serviceLegalBasis.setCarry_out_code(carryOutCode);
               serviceLegalBases.add(serviceLegalBasis);
               //保存相关法律内容
               List<Map<String, Object>> lawTerm = JsonUtil.jsonToList(legalBasis.get(i).get("law_term")==null?null:legalBasis.get(i).get("law_term").toString());
               if (lawTerm.size() != 0){
                   ServiceLawterm serviceLawterm = new ServiceLawterm();
                   serviceLawterm.setContent(lawTerm.get(0).get("content")==null?null:lawTerm.get(0).get("content").toString());
                   serviceLawterm.setBasis_code(serviceLegalBasis.getBasis_code());
                   serviceLawterm.setSort_num(lawTerm.get(0).get("sort_num")==null?null:lawTerm.get(0).get("sort_num").toString());
                   serviceLawterm.setTerm_name(lawTerm.get(0).get("term_name")==null?null:lawTerm.get(0).get("term_name").toString());
                   serviceLawterms.add(serviceLawterm);
               }
            }
            if (serviceLegalBases.size() != 0){
                serviceLegalBasisDAO.insertList(serviceLegalBases);
            }
            if (serviceLawterms.size() != 0){
                serviceLawtermDAO.insertList(serviceLawterms);
            }

            //办理窗口
            List<Map<String, Object>> windows = JsonUtil.jsonToList(item.getString("windows"));
            for (int i = 0;i<windows.size();i++){
                ServiceWindow serviceWindow = new ServiceWindow();
                serviceWindow.setName(windows.get(i).get("name")==null?null:windows.get(0).get("name").toString());
                serviceWindow.setWindow_code(windows.get(i).get("window_code")==null?null:windows.get(0).get("window_code").toString());
                serviceWindow.setCarry_out_code(carryOutCode);
                serviceWindow.setOffice_hour(windows.get(i).get("office_hour")==null?null:windows.get(0).get("office_hour").toString());
                serviceWindow.setPhone(windows.get(i).get("phone")==null?null:windows.get(0).get("phone").toString());
                serviceWindow.setOrg_code(windows.get(i).get("org_code")==null?null:windows.get(0).get("org_code").toString());
                serviceWindow.setOrg_name(windows.get(i).get("org_name")==null?null:windows.get(0).get("org_name").toString());
                serviceWindow.setAddress(windows.get(i).get("address")==null?null:windows.get(0).get("address").toString());
                serviceWindow.setTraffic_guide(windows.get(i).get("traffic_guide")==null?null:windows.get(0).get("traffic_guide").toString());
                serviceWindowDAO.insert(serviceWindow);
            }

            //办事材料
            List<Map<String, Object>> submitDocuments = JsonUtil.jsonToList(item.getString("submit_documents"));
            List<ServiceApplicationMaterials> applicationMaterialsList = new ArrayList<>();
            for (int i = 0;i<submitDocuments.size();i++){
                ServiceApplicationMaterials applicationMaterials = new ServiceApplicationMaterials();
                applicationMaterials.setMaterials_id(submitDocuments.get(i).get("materials_id")==null?null:submitDocuments.get(i).get("materials_id").toString());
                applicationMaterials.setInner_code(submitDocuments.get(i).get("inner_code")==null?null:submitDocuments.get(i).get("inner_code").toString());
                applicationMaterials.setMaterials_code(submitDocuments.get(i).get("materials_code")==null?null:submitDocuments.get(i).get("materials_code").toString());
                applicationMaterials.setMaterials_name(submitDocuments.get(i).get("materials_name")==null?null:submitDocuments.get(i).get("materials_name").toString());
                applicationMaterials.setCopy(submitDocuments.get(i).get("copy")==null?null:submitDocuments.get(i).get("copy").toString());
                applicationMaterials.setFilling_requirement(submitDocuments.get(i).get("filling_requirement")==null?null:submitDocuments.get(i).get("filling_requirement").toString());
                applicationMaterials.setStandard_collection(submitDocuments.get(i).get("standard_collection")==null?null:submitDocuments.get(i).get("standard_collection").toString());
                applicationMaterials.setOrigin(submitDocuments.get(i).get("origin")==null?null:submitDocuments.get(i).get("origin").toString());
                applicationMaterials.setElect(submitDocuments.get(i).get("elect")==null?null:submitDocuments.get(i).get("elect").toString());
                applicationMaterials.setMaterials_type(submitDocuments.get(i).get("materials_type")==null?null:submitDocuments.get(i).get("materials_type").toString());
                applicationMaterials.setElectronic_license_code(submitDocuments.get(i).get("electronic_license_code")==null?null:submitDocuments.get(i).get("electronic_license_code").toString());
                applicationMaterials.setElectronic_license(submitDocuments.get(i).get("electronic_license")==null?null:submitDocuments.get(i).get("electronic_license").toString());
                applicationMaterials.setIs_relate_license(submitDocuments.get(i).get("is_relate_license")==null?null:submitDocuments.get(i).get("is_relate_license").toString());
                applicationMaterials.setSubmission_required(submitDocuments.get(i).get("submission_required")==null?null:submitDocuments.get(i).get("submission_required").toString());
                applicationMaterials.setCarry_out_code(carryOutCode);

                //文件模板
                List<Map<String, Object>> sampleFile = JsonUtil.jsonToList(submitDocuments.get(i).get("sample_file")==null?null:submitDocuments.get(0).get("sample_file").toString());
                if (sampleFile.size() != 0){
                    applicationMaterials.setSample_file_url(sampleFile.get(0).get("file_path")==null?null:sampleFile.get(0).get("file_path").toString());
                    applicationMaterials.setSample_file_name(sampleFile.get(0).get("file_name")==null?null:sampleFile.get(0).get("file_name").toString());
                    applicationMaterials.setSample_code(sampleFile.get(0).get("file_id")==null?null:sampleFile.get(0).get("file_id").toString());
                }
                //文件空表
                List<Map<String, Object>> emptyFile = JsonUtil.jsonToList(submitDocuments.get(i).get("empty_file")==null?null:submitDocuments.get(0).get("empty_file").toString());
                if (emptyFile.size() != 0){
                    applicationMaterials.setEmpty_file_url(emptyFile.get(0).get("file_path")==null?null:emptyFile.get(0).get("file_path").toString());
                    applicationMaterials.setEmpty_file_name(emptyFile.get(0).get("file_name")==null?null:emptyFile.get(0).get("file_name").toString());
                    applicationMaterials.setEmpty_code(emptyFile.get(0).get("file_id")==null?null:emptyFile.get(0).get("file_id").toString());
                }
                applicationMaterialsList.add(applicationMaterials);
            }
            serviceApplicationMaterialsDAO.insertList(applicationMaterialsList);
            result.setCode(200);
            result.setData("事项同步成功，事项编码：" + carryOutCode);
        } else {
            //返回查询错误信息
            result.setCode(500);
            result.setData("同步失败，原因="+jsonObject.getString("message"));
        }
       return result;
    }
}
