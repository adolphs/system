/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PlanningBureau
 * Author:   Administrator
 * Date:     2020/12/30
 * History:
 */
package com.wyait.manage.create_department_form;

import com.deepoove.poi.XWPFTemplate;
import net.sf.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 〈规划局〉
 *
 * @author Administrator
 * @create 2020/12/30 9:57
 * @since 1.0.0
 */
public class PlanningBureau {

    private final static String WORD_PATH = "e:\\word_file\\guihua\\";    // 表单模板存储路径

    /**
     * 根据数据集生成表单
     * @param data
     * @param data
     */
    public static void departmentForm(JSONObject data){
        try {
            String tableName = data.get("tableName").toString();   // 表单名称
            if (tableName.contains("用地规划许可证和国有建设用地")){
                //4-建设用地规划许可证和国有建设用地使用权不动产权证书合并办理（不成立项目公司）申请表
                projectApproval(data);
            } else if (tableName.contains("用地规划许可证和土地出让合同变更协议合并办理")){
                //5-建设用地规划许可证和土地出让合同变更协议合并办理（公开出让成立项目公司）申请表
//                filingApplication(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *  1. 建设用地规划许可证和国有建设用地使用权不动产权证书合并办理
     *      广州市规划和自然资源局立案申请表
     */
    private static void projectApproval(JSONObject data){
        String modelName = data.getString("tableName") + ".docx";
        String outputName = "user_" + data.getString("tableName") + ".docx";     //  d://user_file//2021-1-10//建设用地规划许可证和国有建设用地使用权不动产权证书合并办理.docx
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("projectName", data.get("projectName"));   //建设项目名称
                put("beLocated", data.get("beLocated"));       //土地座落
                put("projectCode", data.get("projectCode"));   //项目代码

                /** 申请人（竞得人） */
                put("qlName", data.get("qlName"));    //名称（姓名）
                put("address1", data.get("address1"));   //邮件送达地址
                put("qlPhone", data.get("qlPhone"));     // 电话
                put("postalCode", data.get("postalCode"));     // 邮政编码
                put("institutionsCode", data.get("institutionsCode"));     //统一社会信用代码、组织机构代码、工商登记码或自然人身份证号码
                put("consignor", data.get("consignor"));     //受委托人
                put("officeTel1", data.get("officeTel1"));     //受委托人 --办公电话
                put("mobile1", data.get("mobile1"));     //受委托人 ---手机
                put("attribute1", data.get("attribute1"));     //受委托人 --属性

                /** 申请人（项目公司） */
                put("companyName", data.get("companyName"));    //名称（姓名）
                put("address2", data.get("address2"));   //邮寄送达地址
                put("qlPhone2", data.get("qlPhone2"));     //电话
                put("postalCode2", data.get("postalCode2"));     //邮政编码
                put("institutionsCode2", data.get("institutionsCode2"));     //统一社会信用代码、组织机构代码、工商登记码或自然人身份证号码
                put("trustee", data.get("trustee"));     //受委托人
                put("officeTel2", data.get("officeTel2"));     //受委托人 --办公电话
                put("mobile2", data.get("mobile2"));     //受委托人 ---手机
                put("attribute2", data.get("attribute2"));     //受委托人 --属性

                put("acceptanceUnit", data.get("acceptanceUnit"));     //受理部门
                put("handlingUnit", data.get("handlingUnit"));     //办理部门
                put("filingCategory", data.get("filingCategory"));     //立案类别
                put("reliefMapNo", data.get("reliefMapNo"));     //地形图号
                put("acreage", data.get("acreage"));     //申请总用地面积（净用地面积）
                put("purpose", data.get("purpose"));     //征地现状土地类别

                /** 建设用地概况  */
                put("approvalAuthority", data.get("approvalAuthority")); //立项批准机关
                put("totalInvestment", data.get("totalInvestment"));   // 投资总额
                put("keyAttributes", data.get("keyAttributes"));    // 项目重点属性
                put("approvalNo", data.get("approvalNo"));    //批准文件文号
                put("projectStatus", data.get("projectStatus"));    //项目现状
                put("specialProperties", data.get("specialProperties")); //特殊性质
                put("explain", data.get("explain")); // 关于申请事项的具体说明

                // 历史批复情况
                /*put("businessSituation",new NumbericRenderData(new ArrayList<TextRenderData>(){{
                    add(new TextRenderData("FF00FF", "1.《规划设计条件》：穗国土规划业务函〔20××〕×××号；"));
                    add(new TextRenderData("FF00FF", "2.《成交确认书》：广州公资交（土地）字〔20××〕第×××号；"));
                    add(new TextRenderData("FF00FF", "3.《国有建设用地使用权出让合同》：4401××-2018-0000××；"));
                    add(new TextRenderData("FF00FF", "4.《成交确认书》约定是否成立项目公司：□是，□否；"));
                    add(new TextRenderData("FF00FF", "5.是否签订《国有建设用地使用权出让合同变更协议》：□是，□否；"));
                    add(new TextRenderData("FF00FF", "6.是否缴齐国有建设用地使用权出让价款、利息和违约金：□是，□否；"));
                    add(new TextRenderData("FF00FF", "7.是否缴齐契税：□是，□否。"));
                }}));*/
                put("businessSituation1", data.get("businessSituation1"));
                put("businessSituation2", data.get("businessSituation2"));
                put("businessSituation3", data.get("businessSituation3"));
                put("businessSituation4", data.get("businessSituation4"));
                put("businessSituation5", data.get("businessSituation5"));
                put("businessSituation6", data.get("businessSituation6"));
                put("businessSituation7", data.get("businessSituation7"));

                put("serviceModel", data.get("serviceModel"));  //文书送达方式
                put("date", data.get("date"));   //申请日期
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


    /**
     *  2. 建设用地规划许可证和土地出让合同变更协议合并办理（公开出让成立项目公司）申请表
     */
    private static void  filingApplication (JSONObject data){
        String modelName="广州市规划和自然资源局立案申请表.docx";
        String outputName="user_广州市规划和自然资源局立案申请表.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("data", data.get("date"));   //申请日期
                put("registerCode", data.get("registerCode"));    //立案编号 （确定立案后填写）

                put("projectName", data.get("projectName")); //建设项目名称
                put("industryCategory", data.get("industryCategory")); //行业分类
                put("address", data.get("address"));   //建设项目地址
                put("projectCode", data.get("projectCode"));   // 项目统一代码

                /** 申请人 */
                put("name", data.get("name"));    //名称(姓名)
                put("address2", data.get("address2"));   //地址
                put("contacts", data.get("contacts"));    //联系人
                put("phone", data.get("phone"));     //电话
                put("mobile", data.get("mobile"));    //手机
                put("postalCode", data.get("postalCode"));     // 邮政编码
                put("number", data.get("number"));     // 组织机构代码或自然人身份证号码

                /** 受委托人 */
                put("trustee", data.get("trustee"));    //受委托人
                put("officeTel", data.get("officeTel"));    //联系电话 --办公
                put("mobile2", data.get("mobile2"));       //联系电话 --手机
                put("attribute","☑" + data.get("attribute"));  //属性 :  □行政机关   □事业单位   □企业单位   □驻穂部队   □其他单位   □个人

                /** 受理部门 */
                put("acceptanceDepartment", data.get("acceptanceDepartment"));

                /** 办理部门 */
                put("handleDepartment", data.get("handleDepartment"));

                /** 立案类别 */
                put("filingCategory", data.get("filingCategory"));

                /**  取得土地方式 */
                put("way", "☑" + data.get("way"));

                /**  用地情况自报 */
                put("selfReport", "1.是否已按规划验收和房屋建筑面积测绘成果报告签订《国有建设用地使用权出让合同变更协议》或取得划拨决定书变更复函：□是，□否\n" +
                        "2.是否已按土地出让合同（变更协议）或划拨决定书（变更复函）缴齐国有建设用地使用权出让价款、利息和违约金：☑是，□否");

                /**  文证编号 */
                put("agreNumber1", data.get("agreNumber1"));   //土地出让合同及其变更协议编号：
                put("agreNumber2", data.get("agreNumber2"));  //划拨决定书及其变更复函编号：
                put("agreNumber3", data.get("agreNumber3"));   //土地出让金缴交凭证编号：

                /**  申请内容及相关说明 */
                put("contentExplain", data.get("contentExplain"));

                /**  送达方式 */
                put("serviceModel", "☑" + data.get("serviceModel"));  //□直接到窗口领取  □邮政速递（邮费到付） □自助取件柜领取  □网上送达
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
