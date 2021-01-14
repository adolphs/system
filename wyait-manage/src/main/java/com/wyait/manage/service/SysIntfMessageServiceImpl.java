/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SysIntfMessageServiceImpl
 * Author:   Administrator
 * Date:     2021/1/11
 * History:
 */
package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wyait.manage.create_department_form.*;
import com.wyait.manage.dao.SysIntfMessageDao;
import com.wyait.manage.pojo.SysIntfMessage;
import com.wyait.manage.service.db1.ProjectConstructionApprovalService;
import com.wyait.manage.service.db1.SysIntfMessageService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2021/1/11 13:18
 * @since 1.0.0
 */
@Service
public class SysIntfMessageServiceImpl extends ServiceImpl<SysIntfMessageDao, SysIntfMessage> implements SysIntfMessageService {

    @Autowired
    private SysIntfMessageDao messageDao;
    @Autowired
    private ProjectConstructionApprovalService  approvalService;

    /**
     *  1. 按照部门区分相应模板文件
     *  2. 根据表单ID或者表单名找对应的模板填充数据、生成各个申请表格
     * @param formId        表单
     * @return
     */
    public void distributionData(String formId) {
        Calendar now = Calendar.getInstance();
       /* Long time = new Date().getTime();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");*/
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        JSONObject data = new JSONObject();
        data.put("date", dateFormat.format(now.getTime()));
        String path = "e:\\user_form\\"+ data.get("date") +"\\";   //表单存放路径
        data.put("outputPath", path);

        //查询用户+ 表单数据
        List<Map<String, Object>> formDepartmentList = messageDao.feachFormDepartment(formId);  //用户数据：  部门+ 表单名称
        List<Map<String, Object>> feachUserFormList = messageDao.feachUserFormList(formId);   //用户表单数据
        List<Map<String, Object>> fileList = messageDao.feachMaterialList("1349167434100613120");          //用户上传附件文件

        System.out.println(formDepartmentList.size()+"----------------formDepartmentList--------------" + formDepartmentList);
        System.out.println(feachUserFormList.size()+"----------------feachUserFormList--------------" + feachUserFormList);
        if (formDepartmentList!=null && feachUserFormList.size()>0){
            for (Map<String, Object> feufl:feachUserFormList){
                data.put(feufl.get("form_field_name"), feufl.get("form_value"));
            }
        }

        try {
            //文件不存在则创建该文件
            File outputPath = new File(path);
            if (!outputPath.exists()){
                System.out.println("file not exists, create it ..." + outputPath);
                outputPath.mkdirs();   //创建文件
            }

            for (Map<String, Object> fdl:formDepartmentList){
                data.put("tableName", fdl.get("form_main_name"));   //表单名称
                String department = fdl.get("department_name").toString();  //部门名称
                if (department.contains("规划和自然资源局")) {
                    //
                    PlanningBureau.departmentForm(data);  // 表单表数据集
                } else if (department.contains("公安分局")) {
                    //
                    PublicSecurity.departmentForm("", "");
                } else if (department.contains("市场监管局")) {

                } else if (department.contains("发展和改革局")) {
                    //
                    DevelopmentReformBureau.departmentForm(data,"");
                } else if (department.contains("交通运输局")) {
                    //
                    TrafficDepartment.departmentForm("", "");
                } else if (department.contains("住房和城乡建设局")) {
                    //
                    HousingConstruction.departmentForm(data, "");
                } else if (department.contains("城管部门")) {
                    //
                    UrbanManagement.departmentForm("", "");
                }
            }
            //推送表单
            approvalService.pushInformation(data, fileList);
        } catch (Exception e) {
            //
            System.out.println("----------FormDataDistribution-------------表单数据分发异常：" + e);
//            logger.error("----------表单数据分发异常：-------------" + e);
        }

    }



    @Override
    public List<Map<String, Object>> feachUserFormList(String formId) {

        return messageDao.feachUserFormList(formId);
    }

    @Override
    public List<Map<String, Object>> feachFormDepartment(String formId) {
        return messageDao.feachFormDepartment(formId);
    }

    @Override
    public List<Map<String, Object>> feachMaterialList(String formId) {
        return messageDao.feachMaterialList(formId);
    }
}
