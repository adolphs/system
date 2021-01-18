/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: FormDataDistribution
 * Author:   Administrator
 * Date:     2021/1/6
 * History:
 */
package com.wyait.manage.create_department_form;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 〈用户填写表单数据分发〉
 *
 * @author Administrator
 * @create 2021/1/6 13:07
 * @since 1.0.0
 */
public class FormDataDistribution {

    private static final Logger logger = LoggerFactory.getLogger(FormDataDistribution.class);

    /**
     *  1. 按照部门区分相应模板文件
     *  2. 根据表单ID或者表单名找对应的模板填充数据、生成各个申请表格
     * @param userId        用户
     * @param formId        表单
     * @param department    部门
     * @return
     */
    public static void distributionData(String userId, String formId, String department) {
        Calendar now = Calendar.getInstance();
       /* Long time = new Date().getTime();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");*/
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        JSONObject data = new JSONObject();
        data.put("formId", formId);
        data.put("date", dateFormat.format(now.getTime()));
        String path = "e:\\user_form\\"+ data.get("date") +"\\";   //表单存放路径
        data.put("outputPath", path);

        try {


            File outputPath = new File(path);
            if (!outputPath.exists()){
                System.out.println("file not exists, create it ..." + outputPath);
                //文件不存在则创建该文件
                //这里是在指定的抽象路径c盘根目录下创建a.txt
                //注意此处有可能出现IOException,要进行异常处理
                outputPath.mkdirs();   //创建文件目录
                System.out.println("文件创建成功!");
            }

            if (department.contains("规划")) {
                //
//                PlanningBureau.departmentForm(data,  "");  // 哪一个表， 表数据集
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
        } catch (Exception e) {
            //
            System.out.println("----------FormDataDistribution-------------表单数据分发异常：" + e);
//            logger.error("----------表单数据分发异常：-------------" + e);
        }

    }

}
