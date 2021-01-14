/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HousingConstruction
 * Author:   Administrator
 * Date:     2020/12/29
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
 * 〈住建局〉
 *
 * @author Administrator
 * @create 2020/12/29 11:23
 * @since 1.0.0
 */
public class HousingConstruction {

    private final static String WORD_PATH = "e:\\word_file\\zhujian\\";   // 表单模板存储路径

    /**
     * 根据数据集生成表单
     * @param data
     * @param tableId
     */
    public static void departmentForm(JSONObject data, String tableId){
        try {
            if (tableId.contains("工程规划许可证和施工许可证")){
                //工程规划许可证和施工许可证并联审批申请表
                constructionPlanPermit(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 1. 工程规划许可证和施工许可证并联审批申请表
     * （社会投资简易低风险项目专用）
     * @param data
     * @return
     */
    private static void constructionPlanPermit(JSONObject data){
        String modelName="工程规划许可证和施工许可证并联审批申请表.docx";
        String outputName="user_工程规划许可证和施工许可证并联审批申请表.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("projectName", data.get("projectName"));   //建设项目名称

                //建设区位
                put("area","☑" + data.get("area"));
                put("address", data.get("address"));            //建设地址
                put("projectCode", data.get("projectCode"));    //项目代码
                put("contractNo", data.get("contractNo"));  //土地出让合同号

                /** 申请人 */
                put("applyName", data.get("applyName"));          //名称（姓名）
                put("applyAddress", data.get("applyAddress"));          //地址
                put("applyPhone", data.get("applyPhone"));          //电话
                put("postalCode", data.get("postalCode"));          //邮政编码
                put("applyNumber", data.get("applyNumber"));          //组织机构代码或自然人身份证号码
                put("trustee", data.get("trustee"));          //受委托人
                put("contactTele", data.get("contactTele"));          //联系电话--办公
                put("contactMobile", data.get("contactMobile"));          //联系电话--手机
                put("attribute", "☑" + data.get("attribute"));          //属性：□行政机关□事业单位□企业单位□驻穂部队□其他单位□个人

                /** 基本信息 */
                put("licenseKey", data.get("licenseKey"));          //用地规划许可证号
                put("contractPrice", data.get("contractPrice"));          //施工总承包合同价格
                put("contractDuration", data.get("contractDuration"));          //合同工期
                put("investigationUnit", data.get("investigationUnit"));          //勘察单位
                put("investigationUnitName", data.get("investigationUnitName"));          //勘察单位项目负责人
                put("designUnit", data.get("designUnit"));          //设计单位
                put("designUnitName", data.get("designUnitName"));          //设计单位项目负责人
                put("constructionUnit", data.get("constructionUnit"));          //施工总承包单位
                put("constructionUnitName", data.get("constructionUnitName"));          //施工单位项目负责人
                put("supervisorUnit", data.get("supervisorUnit"));          //监理单位
                put("supervisorName", data.get("supervisorName"));          //总监理工程师
                put("serviceModel", "□" + data.get("serviceModel"));          //文书送达方式： □邮政速递□施工现场发放
                put("applySign", "user1");          // 申请人（签名）
                put("applyDate", data.get("date"));          // 申请日期
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
