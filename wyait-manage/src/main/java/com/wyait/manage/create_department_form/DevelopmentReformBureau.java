/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: DevelopmentReformBureau
 * Author:   Administrator
 * Date:     2021/1/4
 * History:
 */
package com.wyait.manage.create_department_form;

import com.deepoove.poi.XWPFTemplate;
import net.sf.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈发展和改革局〉
 *
 * @author Administrator
 * @create 2021/1/4 14:48
 * @since 1.0.0
 */
public class DevelopmentReformBureau {

    private final static String WORD_PATH = "e:\\word_file\\fagaiju\\";    // 表单模板存储路径

    /**
     * 根据数据集生成表单
     * @param data
     * @param tableId
     */
    public static void departmentForm(JSONObject data, String tableId){
        try {
            if (tableId.contains("企业投资项目备案表")){
                //广东省企业投资项目备案表（申请新增）
                investmentRecord(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 1. 广东省企业投资项目备案表（申请新增）
     * @param data
     * @return
     */
    private static void investmentRecord(JSONObject data){
        //图片路径，请注意你是linux还是windows
        String modelName="工程规划许可证和施工许可证并联审批申请表.docx";
        String outputName = "user_工程规划许可证和施工许可证并联审批申请表.docx"; //用户名_表名字.docx
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                /**  企业基本情况 */
                put("enterpriseName", data.get("enterpriseName"));   //企业名称
                put("economicType", data.get("economicType"));     //经济类型 ： 1.国有独资 2.国有控股 3.集体 4.私营5.个体 6.联营 7.股份制 8.其它
                put("organizationCode", data.get("organizationCode"));          //组织机构代码
                put("licenseNumber", data.get("licenseNumber"));    //营业执照注册号
                put("legalPerson", data.get("legalPerson"));  //企业法人代表姓名及联系方式
                put("legalPersonNo", data.get("legalPersonNo"));   //企业法人代表身份证件类型及号码
                put("agent", data.get("agent"));          //企业经办人姓名及联系方式（含固话）
                put("agentNo", data.get("agentNo"));          //企业经办人身份证件类型及号码

                /** 项目基本情况 */
                put("constructionCategory", data.get("constructionCategory"));          //建设类别： 1.基建 2.更改
                put("constructionNature", data.get("constructionNature"));          //建设性质 ：  1.基建 2.扩建 3.改建 4.其他
                put("xmjsqjyjddjygw", data.get("xmjsqjyjddjygw"));          //项目建设期间预计带动就业岗位
                put("xmjctchyjddjygw", data.get("xmjctchyjddjygw"));          //项目建成投产后预计带动就业岗位
//                put("organizationNo", "");          //组织机构代码或自然人身份证号码
                put("constructionScale", data.get("constructionScale"));          //建设规模及内容
                put("builtUpArea", data.get("builtUpArea"));          //建筑面积
                put("areaCovered", data.get("areaCovered"));          //占地面积
                put("industryCode", data.get("industryCode"));          //行业代码

                /** 项目总投资*/
                put("totalInvestment", data.get("totalInvestment"));          //总投资
                put("capital1", data.get("capital1"));          //自由资金
                put("capital2", data.get("capital2"));          //资本金
                put("capital3", data.get("capital3"));          //国内贷款
                put("capital4", data.get("capital4"));          //土建投资
                put("capital5", data.get("capital5"));          //股票债券
                put("capital6", data.get("capital6"));          //设备及技术投资
                put("capital7", data.get("capital7"));          //其他资金
                put("capital8", data.get("capital8"));          //进口设备用汇

                put("plannedStartTime", data.get("plannedStartTime"));          //计划开工时间
                put("plannedEndTime", data.get("plannedEndTime"));          //计划竣工时间
                put("cellPhone", data.get("cellPhone"));          //联系人手机
                put("mailBox", data.get("mailBox"));          //电子邮箱
            }
        };

        XWPFTemplate template = XWPFTemplate.compile(WORD_PATH + modelName)
                .render(datas);
        FileOutputStream out;
        try {
            out = new FileOutputStream(data.getString("outputPath") + outputName);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
