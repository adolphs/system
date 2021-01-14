/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ProjectConstructionApprovalServiceImpl
 * Author:   Administrator
 * Date:     2021/1/14
 * History:
 */
package com.wyait.manage.service;

import com.wyait.manage.dao.SysIntfParameterDAO;
import com.wyait.manage.service.db1.ProjectConstructionApprovalService;
import com.wyait.manage.utils.HttpClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈市工程建设项目联合审批平台对接业务实现类〉
 *
 * @author Administrator
 * @create 2020/12/28 13:58
 * @since 1.0.0
 */
@Service
public class ProjectConstructionApprovalServiceImpl implements ProjectConstructionApprovalService {

    @Autowired
    private SysIntfParameterDAO sysIntfParameterDAO;

    /**
     *  3.1 登录接口
     * @return   access_token
     */
    private static String logon() {
        /**
         * http://api2.gzonline.gov.cn:9090/oauth/token?client_id=20171213180514100100&client_secret=DB9BC834BDCC42D1A24BC8BB40798408
         */
        String getUrl = "http://api2.gzonline.gov.cn:9090/oauth/token";  //测试环境
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "20171213180514100100");
        map.put("client_secret", "DB9BC834BDCC42D1A24BC8BB40798408");
        String resultString = HttpClient.doGet(getUrl, map);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        // {"access_token":"e09877a90dfe494d9d8e3456f4af2a91","token_type":"bearer","expires_in":3600}
        return jsonObject.getString("access_token");
    }

    /**
     * 3.2 项目类型接口
     * @return
     */
    @Override
    public String getProjectCategory(String accessToken) {
        String response = "";
        /**
         * http://{API_ROOT}/api/blsp/listProjectCategory?access_token={access_token}
         */
        String url = "http://api2.gzonline.gov.cn:9090/api/blsp/listProjectCategory";  //测试环境
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("access_token", accessToken);
        String resultString = HttpClient.doGet(url, paramsMap);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null) {
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("data"));
            response = jsonObject.getString("data");
        }
        return response;
    }

    /**
     * 3.3 项目阶段信息接口
     * @return
     */
    @Override
    public String getFlowInfoByProjectCategory(String accessToken) {
        String responseText = "";
        /**
         *   番禺区代码regionCode = 440113000000
         */
        String url = "http://api2.gzonline.gov.cn:9090/api/blsp/getFlowInfoByProjectCategory";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("access_token", accessToken);
        paramsMap.put("typeCode", "0");
        paramsMap.put("categoryCode",  "1");
        paramsMap.put("regionCode", "440113000000");   // 番禺区
        String resultString = HttpClient.doGet(url, paramsMap);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null) {
            JSONObject resultData = JSONObject.fromObject(jsonObject.getString("data"));
            responseText = jsonObject.getString("data");
        }
        return responseText;
    }

    /**
     * 3.4获取阶段共性材料信息接口
     * @return     @ARTHUR  . JYDFXBDCDJTYBD 简易低风险不动产登记通用表单
     */
    @Override
    public String getProjectBusinessMessage(String accessToken, String id, String itemCodes) {
        String access_token = "";
        /**
         * http://api2.gzonline.gov.cn:9090/api/blsp/getProjectBusinessMessage     接口url以互联互通平台为准
         */
        String getUrl = "http://api2.gzonline.gov.cn:9090/api/blsp/getProjectBusinessMessage?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("flowId", "20171213180514100100");
        params.put("itemCodes", "DB9BC834BDCC42D1A24BC8BB40798408");

        String resultString = HttpClient.doGet(getUrl,params);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        return access_token;
    }


    /**
     *  3.25.2提交全流程表单信息接口
     * @return  dataId    获取表单ID
     */
    public static String getDataId(String accessToken, String formId) {
        String dataId = "";
        String url = "http://10.194.252.58:8082/Service/saveDataEx";    //测试环境
        Map<String, String> params = new HashMap<>();
        params.put("formId", "JYDFXBDCDJTYBD");    //表单ID
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("XMDM", "");    // 项目代码
//        jsonObject.put("SQR", "黄卓立,黄奕飞");   //
//        jsonObject.put("SQRZJZL", "身份证,身份证");       //
//        jsonObject.put("SQRZZDM", "440184198610066332,440184198801016338");   //
//        jsonObject.put("JZMJ", "111.61");  //建筑面积
//        jsonObject.put("BDCDYH","440184001016GB00009F00800039");   //
//        jsonObject.put("BDCQZH", "粤(2020)广州市不动产权第09400110号");   //
//        jsonObject.put("QX", "转移登记(二手交易或互换)");   //
//        jsonObject.put("QLLX", "4");   //
//        jsonObject.put("DJLX","200");   //
//        jsonObject.put("FDZL","广州市从化区响泉路1号903房");   //
//        jsonObject.put("FWXZMC","市场化商品房");   //
//        jsonObject.put("ZCS","17");         //
//        jsonObject.put("FWJG", "钢筋混凝土结构");  //
//        jsonObject.put("DJSJ", "2020-09-01 10:04:5");   //
//        jsonObject.put("FJ", "根据穗府办函[2017]65号文，该房屋须满2年后方可转让或办理析产手续，从2020年9月1日起。");   //
        params.put("formData", jsonObject.toString());    // 表单数据
        JSONObject responseJson = JSONObject.fromObject(HttpClient.doPost(url, params));
        //{"dataId":"20201215104652366900","state":"200"}
        if ("200".equals(responseJson.get("state")) && responseJson!=null){
            dataId = responseJson.getString("dataId");
        }
        return dataId;
    }

    /**
     * 3.5提交网上申报信息接口
     * @return
     */
    public String pushInformation(JSONObject data, List<Map<String, Object>> files) {
        String accessToken = logon();    //获取token
        String dataId = getDataId(accessToken, null);   //获取表单ID
        String responseText = "";
        /**
         * 获取联办分组事项及相关材料信息接口
         */
//        String getUrl = "http://api2.gzonline.gov.cn:9090/api/inspur/blsp/webApply_all?SystemCode=20180929000000440100&access_token=473db01a626940299d4a8cda6de9e92a";  //正式环境

        String getUrl = "http://10.194.252.58:8082/Service/gz/parallel/building/webApply?SystemCode=20180929000000440100&access_token=" + accessToken;   //测试环境
        Map<String, Object> params = new HashMap<>();

        params.put("flowId", "key-20180926164742125500:1:439a3f36-c229-11e8-8ac4-d00dd26c0510");    //阶段ID    3.3返回的ID
        params.put("formId", "SHTZ_LXYDGHXKJD");     //通用表单ID     3.4返回 字段commonFormId
        params.put("formDataId", dataId);  //通用表单数据ID      3.25.2返回，表单数据id   "20190611162852758300"
        params.put("regionCode", "440113000000");      //区划代码    取番禺区固定值
        params.put("regionName", "番禺区");            // 区划名称      取番禺区固定值

        /* 项目基本信息*/
        JSONObject info = new JSONObject();
        info.put("projectType", "1");   //项目类型，从接口3.2获取
        info.put("projectCategory", "3");  //项目类别  从接口3.2获取
        info.put("projectName", data.get("projectName"));   //项目名称   ---  ＫＸＣ－Ｐ７－１地块
        info.put("projectCode", "2018-440112-47-03-834191");   //项目编码
        info.put("projectAllowedNo", "");    //项目审批核准备案文号
        info.put("contractor", data.get("qlName"));      //申请单位
        info.put("companyCode", "A00008");   //企业注册号   "A00008"
        info.put("companyType", data.get("attribute2"));  //企业类型：110为国有企业，120为集体企业，130为股份合作企业，140为联营企业，150为有限责任公司，160为股份有限公司，170为私营企业，190为其他企业，200为港、澳、台商投资企业，300为外商投资企业
        info.put("companyName", data.get("companyName"));   //企业名称   "广州市广园市政建设有限公司"
        info.put("linkManNo", "440102197112312319");    //申请人证件号   "440102197112312319"   data.get("institutionsCode")
        info.put("location", data.get("beLocated"));   // 项目地址  "二沙岛晴澜路下穿广州大桥段，西起灵洲街，东至晴波路"
        info.put("linkMan", data.get("qlName"));    // 申请人   "text"
        info.put("linkPhone", "13169113260");   //联系电话   "13539411110"
        info.put("projectContent", data.get("explain"));   //建设内容    "建设内容。。。。。。"
        info.put("areaAll", data.get("acreage"));//总用地面积（平方米）   "212"
        info.put("areaBuild", data.get("acreage"));//建设面积（平方米）    “32”
        info.put("investment", data.get("totalInvestment"));//总投资（万元）   "223"
        params.put("info", info);

        /* 经办人信息*/
        JSONObject servicelist = new JSONObject();
        servicelist.put("ApplicantName", data.get("qlName"));   //申请人名称   "测试"
        servicelist.put("ApplicantID", "445224199402081317");   //申请人身份证号   "445224199402081317"    institutionsCode
        servicelist.put("ApplicantMobile", "13169113260");    // 申请人手机号    "13244566785"
        servicelist.put("ApplicantAddress", data.get("address1"));           //申请人地址    "1"
        servicelist.put("CardType", "0");       // 收件方式： 0位大厅送件，1为上门收件       "1"   data.get("serviceModel")
        servicelist.put("CityCode", "511400");    //寄件人城市编码
        servicelist.put("Addressee", "1");    //寄件地址
        servicelist.put("AddresseeName", "22");  //寄件人姓名
        servicelist.put("AddresseeMobile", "22");  //寄件人手机号
        servicelist.put("SendType", "0");      //出证收件方式：0位自取，1位投递
        servicelist.put("SendCityCode", "22");   //收件人城市编码
        servicelist.put("SendAddressee","111");   //收件详细地址
        servicelist.put("SendName", "11");   //收件人姓名
        servicelist.put("SendMobile", "11");  //收件人手机号
        servicelist.put("HallCode", "111");     //大厅号
        servicelist.put("HallName","312");      //大厅名称
        params.put("servicelist", servicelist);

        /* 事项信息*/
        JSONObject items = new JSONObject();
        items.put("DataId", "");  //表单数据ID
        items.put("FormId", "SHTZ_LXYDGHXKJD");  //表单编号 *
        items.put("ItemId", "11440100007482559U3442001001000");   //事项ID *
        items.put("ItemName", "企业投资项目备案");          // 事项名称  *
        items.put("InnerCode", "11440100007482559U3442001001000");  //编办规定事项编码 （用于数据交换）*
        items.put("OrgCode", "007482559");      //事项所属单位 *
        items.put("ItemCode", "11440100007482559U3442001001000");    //事项所属单位名称
        items.put("CaseId", "0");  //  事项所选情形ID *
        items.put("CaseName", "默认情形"); // 事项所选情形

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(items);
        params.put("items", jsonArray);

        /* 材料信息*/
        JSONArray jsonArray2 = new JSONArray();
        JSONObject materials = null;
        if (files != null && files.size() > 0) {
            for (Map<String, Object> fis:files){
                materials = new JSONObject();
                materials.put("DOCUMENT_ID", "9e0448d0-5dfb-e3fc-b497-26cd6b562f66");  //材料ID（事项系统获取），若为共性材料，此值传共性材料code
                materials.put("DOCUMENT_NAME", "立案申请函");  //材料名称（事项系统获取）
                materials.put("TYPE","1");   //文件类型( 0：纸质；1：电子文档；2：审批结果材；3：联合评审方案；6：容缺材料；7：承诺材料；8：电子证照；9：联合审图结果文件； )
                materials.put("FILE_NAME", "fileName");  // 上传文件名称
                materials.put("FILE_PATH", "http://10.194.252.56:8083/WebDiskServerDemo/doc?doc_id=ded44266-8c22-11e9-95c4-d00d80981a7e");  //附件存储路径
                materials.put("FILE_AUTH_CODE", "");  //若TYPE值为3，即联合评审方案，此值为联合评审方案文件下载地址；若TYPE值为8，即电子证照，此值为电子证照授权码；若TYPE值为9，即联合审图结果文件，此值为文件下载地址。
                materials.put("LHPSUI_ PATH", "");   //若TYPE值为3，即联合评审方案，此值为联合评审页面UI地址，可直接浏览
                materials.put("ITEM_ID",  "");    //事项Id（非必填），若为空，当前材料为共性材料
                jsonArray2.add(materials);
            }
        }
        params.put("materials", jsonArray2);
        JSONObject materials2 = new JSONObject();
        materials2.put("DOCUMENT_ID", "9e0448d0-5dfb-e3fc-b497-26cd6b562f66");  //材料ID（事项系统获取），若为共性材料，此值传共性材料code
        materials2.put("DOCUMENT_NAME", data.getString("tableName"));  //材料名称（事项系统获取）
        materials2.put("TYPE","1");   //文件类型( 0：纸质；1：电子文档；2：审批结果材；3：联合评审方案；6：容缺材料；7：承诺材料；8：电子证照；9：联合审图结果文件； )
        materials2.put("FILE_NAME", "fileName");  // 上传文件名称
//        System.out.println("===============存储路径=========================" + data.getString("outputPath") + data.get("tableName") + ".docx");
        materials2.put("FILE_PATH", data.getString("outputPath") + data.get("tableName") + ".docx");  //附件存储路径
        materials2.put("FILE_AUTH_CODE", "");  //若TYPE值为3，即联合评审方案，此值为联合评审方案文件下载地址；若TYPE值为8，即电子证照，此值为电子证照授权码；若TYPE值为9，即联合审图结果文件，此值为文件下载地址。
        materials2.put("LHPSUI_ PATH", "");   //若TYPE值为3，即联合评审方案，此值为联合评审页面UI地址，可直接浏览
        materials2.put("ITEM_ID",  "");    //事项Id（非必填），若为空，当前材料为共性材料
        jsonArray2.add(materials2);    //填写的表格，
        responseText = HttpClient.doPostJson(getUrl, JSONObject.fromObject(params).toString());
//        System.out.println("--------------联合申报数据提交-----responseText----------" + responseText);
//        {"code":"200","data":{"associationNumber":"lh202012140200777201"},"msg":"操作成功！"}
        return responseText;
    }

    /**
     * 3.20项目联办进度信息查询接口
     * @return
     */
    public String queryProgress(String accessToken, String projectCode) {
        /**
         * http://10.194.252.58:8082/Service/gz/parallel/building/getProjectInfo?access_token=  //测试环境
         */
        String url = "http://10.194.252.58:8082/Service/gz/parallel/building/getProjectInfo?access_token=" + accessToken;   //测试环境
        Map<String, Object> params = new HashMap<>();
        params.put("projectCode", "2018-440112-47-03-834191");
        String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
        JSONObject jsonObject = JSONObject.fromObject(responseText);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null){
            return jsonObject.getString("data");
        } else {
            return "{'err_msg':'请稍后在查询!'}";
        }
    }
}
