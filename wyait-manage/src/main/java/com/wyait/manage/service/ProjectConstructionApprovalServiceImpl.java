package com.wyait.manage.service;
import com.wyait.manage.dao.*;
import com.wyait.manage.pojo.SerialNumber;
import com.wyait.manage.pojo.SysIntfMessage;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.UserFile;
import com.wyait.manage.service.db1.ProjectConstructionApprovalService;
import com.wyait.manage.utils.HttpClient;
import com.wyait.manage.utils.HttpUtils;
import groovy.transform.ThreadInterrupt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
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

    private static final Logger logger = LoggerFactory.getLogger(ProjectConstructionApprovalServiceImpl.class);

    @Autowired
    private SysIntfParameterDAO intfParameterDAO;
    @Autowired
    private SysIntfMessageDao sysIntfMessageDao;
    @Autowired
    private SerialNumberDAO serialNumberDAO;
    @Autowired
    private UserBusinessDAO userBusinessDAO;
    @Autowired
    private UserFileDAO userFileDAO;



    /**
     *  3.1 登录接口
     * @return   access_token
     */
    private static String logonAp1(){
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "20171213180514100100");    //互联互通平台——应用系统用户名
        map.put("client_secret", "DB9BC834BDCC42D1A24BC8BB40798408");   //密码
        String resultString = HttpClient.doGet("http://api1.gzonline.gov.cn:9090/oauth/token", map);   //正式环境
        //{"access_token":"1b05950780a742e2861964640edec0e6","token_type":"bearer","expires_in":3600}
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("-------logonAp1（）----------" + jsonObject);
        if (jsonObject != null){
            return jsonObject.getString("access_token");
        }
        return null;
    }

    private static String logonApi2() {
        String getUrl = "http://api2.gzonline.gov.cn:9090/oauth/token";   //正式环境
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "20171213180514100100");
        map.put("client_secret", "DB9BC834BDCC42D1A24BC8BB40798408");
        String resultString = HttpClient.doGet(getUrl, map);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("------登录返回-logon（）----------" + jsonObject);
        // {"access_token":"e09877a90dfe494d9d8e3456f4af2a91","token_type":"bearer","expires_in":3600}
        return jsonObject.getString("access_token");
    }



    /**
     * 3.2 项目类型接口
     *      【对应互联互通：市政务服务数据管理局-联合审批系统-获取项目类型】
     * @return
     */
    @Override
    public String getProjectCategory(String accessToken) {
        String response = "";
        /**
         * http://{API_ROOT}/api/blsp/listProjectCategory?access_token={access_token}
         */
//        String url = "http://api2.gzonline.gov.cn:9090/api/blsp/listProjectCategory?access_token=" + logonApi2(); //正式环境
        String url = "http://10.194.252.58:9016/blsp/web/parallel/gz/listProjectCategory";   //测试环境
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("access_token", accessToken);
        String resultString = HttpClient.doGet(url, paramsMap);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("-------项目类型接口getProjectCategory（）----------" + jsonObject);
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
    public String getFlowInfoByProjectCategory(String accessToken, String type , String category) {
        String responseText = "";
        /**
         *   番禺区代码regionCode = 440113000000
         */
//        String url = "http://api2.gzonline.gov.cn:9090/api/blsp/getFlowInfoByProjectCategory?access_token=" + logonApi2();  //正式环境
        String url = "http://10.194.252.58:9016/blsp/web/parallel/gz/getFlowInfoByProjectCategory";  //测试环境
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("access_token", accessToken);
        paramsMap.put("typeCode", type);   //项目类型编码     0
        paramsMap.put("categoryCode",  category);   //项目业务类型编码   1
        paramsMap.put("regionCode", "440113000000");   // 默认是番禺区。 广州市== 440100000000
        String resultString = HttpClient.doGet(url, paramsMap);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("-------项目阶段信息接口getFlowInfoByProjectCategory（）----------" + jsonObject);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null) {
            JSONObject resultData = JSONObject.fromObject(jsonObject.getString("data"));
            responseText = jsonObject.getString("data");
        }
        return responseText;
    }

    /**
     * 3.31获取项目详细信息接口
     */
    private static String getProjectDetails (String code){
//        String url = "http://api2.gzonline.gov.cn:9090/api/Service/gz/parallel/building/getProjectBaseInfo?access_token=" + logonApi2(); //正式环境
        String url = "http://10.194.252.58:8082/Service/gz/parallel/building/getProjectBaseInfo"; //测试环境
        JSONObject params = new JSONObject();
        params.put("projectCode", code); //项目代码       2019-440104-46-03-026168
        String resultText = HttpClient.doPostJson(url, params.toString());
        JSONObject responseJson = JSONObject.fromObject(resultText);
        System.out.println("-----3.31获取项目详细信息接口getProjectDetails----"  + responseJson);
        //{"code":"200","data":{},"msg":"操作成功！"}
        return responseJson.getString("code");
    }

    /**
     * 3.24同步项目类型接口
     */
    public static String synProjectType(String code, String type, String category, String systemCode) {
//        String url = "http://api2.gzonline.gov.cn:9090/api/inspur/blsp/webApply_all?access_token=" + logonApi2();
        String url = "http://10.194.252.58:8082/Service/gz/parallel/building/syncProjectInfo";  //测试环境
        JSONObject params = new JSONObject();
        params.put("projectCode", code); //项目代码     2019-440104-46-03-026168
        params.put("projectType", type);  //项目类型字典项 ，详细字典项见附录4.2.1       0
        params.put("projectCategory", category);  //项目类别字典项，详细字典项见附录4.2.2       1
        params.put("SystemCode", systemCode);   //系统编码     番禺区20180608091649100000
        String resultString = HttpClient.doPostJson(url, params.toString());
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("-----3.24同步项目类型接口synProjectType------"  + jsonObject);
        //{"code":"200","data":"同步成功","msg":"操作成功！"}
        return jsonObject.getString("code");
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
       /* String getUrl = "http://api2.gzonline.gov.cn:9090/api/blsp/getProjectBusinessMessage?access_token=" + accessToken; //正式环境
//        String getUrl = "http://10.194.252.58:8082/";  //测试
        Map<String, String> params = new HashMap<>();
        params.put("flowId", "20171213180514100100");    //阶段ID
        params.put("itemCodes", "DB9BC834BDCC42D1A24BC8BB40798408");  //事项code

        String resultString = HttpClient.doGet(getUrl,params);   // 返回“commonFormId”通用表单ID
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        System.out.println("--------获取阶段共性材料信息接口getProjectBusinessMessage（）----------" + jsonObject);*/
//        {"code":"200","data":{"commonFormId":"BLSPSGXKJDTYBD","commonMaterials":[],"itemFormIds":[],"specialMaterials":[]},"msg":"操作成功！"}
        return null;
    }


    /**
     *  3.25.2提交全流程表单信息接口     [市政务服务数据管理局-综合受理一对多-网申提交表单]
     * @return  dataId    获取表单ID
     */
    /** 测试环境 */
    public static String getTextDataId(String accessToken, String formId) {
        String dataId = "";
//        String url = "http://10.194.252.58:8082/Service/saveDataEx?access_token=" + accessToken;    //测试环境
        String url = "http://10.194.252.58:8082/Service/saveDataEx?access_token=" ;    //测试环境
        Map<String, String> params = new HashMap<>();
        params.put("formId", "JYDFXBDCDJTYBD");    //表单ID
        JSONObject jsonObject = new JSONObject();
        params.put("formData", jsonObject.toString());    // 表单数据
        String s = HttpClient.doPost(url, params);

        System.out.println("--------s（）----------" + s);
        JSONObject responseJson = JSONObject.fromObject(s);
        System.out.println("--------提交全流程表单信息接口返回getTextDataId（）----------" + responseJson);
        //{"dataId":"20201215104652366900","state":"200"}
        if ("200".equals(responseJson.get("state")) && responseJson!=null){
            dataId = responseJson.getString("dataId");
        }
        return dataId;
    }
    /** 正式环境 */
    public static String getDataId(String accessToken, String formId) {
        String dataId = "";
        String access_token = logonAp1();
        /**
         * http://api1.gzonline.gov.cn:9090/api/inspur/kfpt/zhsl/saveData/1.0?access_token=
         */
        String url = "http://api1.gzonline.gov.cn:9090/api/inspur/kfpt/zhsl/saveData/1.0?access_token=" + access_token;
        JSONObject params = new JSONObject();
        params.put("SystemCode", "20180608091649100000");    //目标系统编号 （由开放平台提供）  [番禺的20180608091649100000]

        JSONObject formData = new JSONObject();
        formData.put("pageRowNum", "10");    //
        formData.put("currPage", "1");   //
        formData.put("totalPage", "1");       //
        formData.put("totalRow", "3");   //
        formData.put("pageIndex", "1");  //
        params.put("formData", formData);

        String responseString = HttpClient.doPostJson(url, params.toString());
        JSONObject jsonObject = JSONObject.fromObject(responseString);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null){
            dataId = JSONObject.fromObject(jsonObject.get("result")).getString("dataId");
        }
//        {"result":"{\"dataId\":\"97ecf9cd94344a9893e51380e80eb5ed\",\"state\":\"200\"}","code":"200","error":""}
        return dataId;
    }

    /**
     * 3.5提交网上申报信息接口
     * @return
     */
    @Override
    @Transactional
    public String pushInformation(JSONObject data, List<Map<String, Object>> files) {
        System.out.println("---data---" + data);
        String responseText = "";
        String accessToken = logonApi2();    //获取token
        String dataId = getTextDataId(accessToken, null);   //获取表单ID
        System.out.println(dataId + "------pushInformation--------accessToken------" + accessToken);
        String projectCode = "2018-440112-47-03-834191";  //项目编码
        String projectType = "1";  //项目类型，从接口3.2获取
        String projectCategory = "3";  //项目类别  从接口3.2获取

        getProjectDetails(projectCode);   // 获取项目详细信息接口
        synProjectType(projectCode, projectType, projectCategory, "");   //同步项目类型

        /**
         * 获取联办分组事项及相关材料信息接口
         * http://10.194.252.58:8082/Service/gz/parallel/building/webApply   测试环境
         */
//        SysIntfParameter sysParameter = intfParameterDAO.feachSysParameter("jsgclhsp");     //正式环境
        SysIntfParameter sysParameter = intfParameterDAO.feachSysParameter("jsgcsp");     //测试环境
        System.out.println("--------------sysParameter-------" +sysParameter);
        String url = sysParameter.getIpPort() + "/api/inspur/blsp/queryPogressInfo?access_token=" + accessToken;
        String getUrl = sysParameter.getIpPort() + "/Service/gz/parallel/building/webApply?SystemCode=20180929000000440100&access_token=" + accessToken;   //测试环境
        Map<String, Object> params = new HashMap<>();

        params.put("flowId", "key-20180926164742125500:1:439a3f36-c229-11e8-8ac4-d00dd26c0510");    //阶段ID    3.3返回的ID
        params.put("formId", "SHTZ_LXYDGHXKJD");     //通用表单ID     取文档阶段表说明
        params.put("formDataId", dataId);  //通用表单数据ID      3.25.2返回，表单数据id   "20190611162852758300"
        params.put("regionCode", "440100000000");      //区划代码 -- 广州市
        params.put("regionName", "广州市");            // 区划名称 -- 广州市
//        params.put("regionCode", "440113000000");      //区划代码    取番禺区固定值
//        params.put("regionName", "番禺区");            // 区划名称      取番禺区固定值

        /* 项目基本信息*/
        JSONObject info = new JSONObject();
        info.put("projectType", projectType);   //项目类型，从接口3.2获取
        info.put("projectCategory", projectCategory);  //项目类别  从接口3.2获取
        info.put("projectName", data.get("projectName"));   //项目名称   ---  ＫＸＣ－Ｐ７－１地块
        info.put("projectName", "ＫＸＣ－Ｐ７－１地块");   //项目名称   ---  ＫＸＣ－Ｐ７－１地块
        info.put("projectCode", projectCode);   //项目编码
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
//        items.put("ItemId", "11440100007482559U3442001001000");   //事项ID *
        items.put("ItemId", "1144011300752367244440116006001");   //事项ID *
//        items.put("ItemName", "企业投资项目备案");          // 事项名称  *
        items.put("ItemName", "河道管理范围内建设项目工程建设方案审批");          // 事项名称  *
//        items.put("InnerCode", "11440100007482559U3442001001000");  //编办规定事项编码 （用于数据交换）*
        items.put("InnerCode", "1144011300752367244440116006001");  //编办规定事项编码 （用于数据交换）*
//        items.put("OrgCode", "007482559");      //事项所属单位 *
        items.put("OrgCode", "007523672");      //事项所属单位 *
//        items.put("ItemCode", "11440100007482559U3442001001000");    //事项所属单位名称
        items.put("ItemCode", "1144011300752367244440116006001");    //事项所属单位名称
        items.put("CaseId", "0");  //  事项所选情形ID *
        items.put("CaseName", "默认情形"); // 事项所选情形

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(items);
        params.put("items", jsonArray);

        /* 材料信息*/
        JSONArray jsonArray2 = new JSONArray();
        JSONObject materials = null;
        if (files != null && files.size() > 0) {
            UserFile userFile = null;
            for (Map<String, Object> fis:files){
                userFile = new UserFile();
                userFile.setUserFileId(fis.get("user_file_id").toString());
                String docid = fileUploadDisk(fis.get("materiaName").toString(), fis.get("file_url").toString()); //推送网盘
//                userFile.setDocid(docid);
//                userFileDAO.updateByPrimaryKeySelective(userFile);
                materials = new JSONObject();
                materials.put("DOCUMENT_ID", "2d65ad6e25074543bb4ccda31272458c");  //材料ID（事项系统获取），若为共性材料，此值传共性材料code
                materials.put("DOCUMENT_NAME", "广东省企业投资项目备案表（新增、变更、延期）"); //fis.get("materiaName"));  //材料名称（事项系统获取）
                materials.put("TYPE","1");   //文件类型( 0：纸质；1：电子文档；2：审批结果材；3：联合评审方案；6：容缺材料；7：承诺材料；8：电子证照；9：联合审图结果文件； )
                materials.put("FILE_NAME", fis.get("file_name"));  // 上传文件名称
                materials.put("FILE_PATH", docid);  //附件(在网盘上的)存储路径
                materials.put("FILE_AUTH_CODE", "");  //若TYPE值为3，即联合评审方案，此值为联合评审方案文件下载地址；若TYPE值为8，即电子证照，此值为电子证照授权码；若TYPE值为9，即联合审图结果文件，此值为文件下载地址。
                materials.put("LHPSUI_ PATH", "");   //若TYPE值为3，即联合评审方案，此值为联合评审页面UI地址，可直接浏览
                materials.put("ITEM_ID",  "");    //事项Id（非必填），若为空，当前材料为共性材料
                jsonArray2.add(materials);
                System.out.println();
            }
        }
        params.put("materials", jsonArray2);
        JSONObject materials2 = new JSONObject();
        materials2.put("DOCUMENT_ID", "9e0448d0-5dfb-e3fc-b497-26cd6b562f66");  //材料ID（事项系统获取），若为共性材料，此值传共性材料code
        materials2.put("DOCUMENT_NAME", data.getString("tableName"));  //材料名称（事项系统获取）
        materials2.put("TYPE","1");   //文件类型( 0：纸质；1：电子文档；2：审批结果材；3：联合评审方案；6：容缺材料；7：承诺材料；8：电子证照；9：联合审图结果文件； )
        materials2.put("FILE_NAME", data.get("tableName"));  // 上传文件名称
        materials2.put("FILE_PATH", "11131767");  //附件存储路径
        materials2.put("FILE_AUTH_CODE", "");  //若TYPE值为3，即联合评审方案，此值为联合评审方案文件下载地址；若TYPE值为8，即电子证照，此值为电子证照授权码；若TYPE值为9，即联合审图结果文件，此值为文件下载地址。
        materials2.put("LHPSUI_ PATH", "");   //若TYPE值为3，即联合评审方案，此值为联合评审页面UI地址，可直接浏览
        materials2.put("ITEM_ID",  "");    //事项Id（非必填），若为空，当前材料为共性材料
        jsonArray2.add(materials2);    //填写的表格，
        logger.info("-----------------JSONObject.fromObject(params).toString() -------------" + JSONObject.fromObject(params).toString());
        responseText = HttpClient.doPostJson(getUrl, JSONObject.fromObject(params).toString());
        JSONObject jsonObject = JSONObject.fromObject(responseText);
        System.out.println("----表单推送建设工程返回------- " + jsonObject);
        /* 记录日志 */
        SysIntfMessage sim = new SysIntfMessage();
        sim.setCreateDate(new Date());
        sim.setModifyDate(new Date());
        sim.setOutUniqueKey("pushInformation");
        sim.setData(JSONObject.fromObject(params).toString());
        sim.setType(1);
        sim.setResultMsg(responseText);
        String status = null;   //推送状态
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null){
//        {"code":"200","data":{"associationNumber":"lh202012140200777201"},"msg":"操作成功！"}
            status = "SUCCESS"; //成功
            String assNo = JSONObject.fromObject(jsonObject.get("data")).getString("associationNumber");
            SerialNumber serialNumber = new SerialNumber();
            serialNumber.setBusinessId(data.getString("businessId"));
            serialNumber.setProgress(1);
            serialNumber.setSerialNumberId(assNo);
            serialNumber.setSerialNumberType(2);
            serialNumber.setSerialNumberValue(assNo);
//            serialNumber.setNewTime(new Date());
            serialNumberDAO.insert(serialNumber);
//            userBusinessDAO.updateAllType(data.getString("businessId"));
        } else {
//          {"code":"300","msg":"编码不存在"}
            status = "FAILURE";   //失败
            responseText = jsonObject.getString("msg");
        }
        sim.setResult(status);
        sysIntfMessageDao.insert(sim);

        return responseText;
    }

    /**
     * 3.20项目联办进度信息查询接口
     * @param projectCode
     * @param associationNumber
     * @return
     */
    @Override
    public String queryProgress(String projectCode, String associationNumber) {
        String accessToken = logonApi2();    //获取token
//        SysIntfParameter sysParameter = intfParameterDAO.feachSysParameter("jsgclhsp");     //正式环境
        SysIntfParameter sysParameter = intfParameterDAO.feachSysParameter("jsgcsp");     //测试环境
        String url = sysParameter.getIpPort() + "/api/inspur/blsp/queryPogressInfo?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("projectCode", "2018-440112-47-03-834191");             //项目编码   2018-440112-47-03-834191
//        params.put("projectCode", "2019-440118-48-02-081219");             //项目编码   2018-440112-47-03-834191
        params.put("associationNumber", "lh202005180100648470");   //联办流水号  lh202005180100648470
        String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
        JSONObject jsonObject = JSONObject.fromObject(responseText);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null){
            return jsonObject.toString();
        } else {
            return responseText;
        }
    }


    /**
     * 5.1项目编码核验服务
     *      [根据项目编码获取检验编码是否存在]
     * @param projectCode  项目编码
     * @return   msg等于200时，核验成功；否则失败
     */
    @Override
    public String proofProjectCode(String projectCode) {
        String msg = null;
        String access_token = logonAp1();  //获取api1登录token
        String url = "http://api1.gzonline.gov.cn:9090/api/zswz/szxspjgpt/checkProjectCode/1.0?access_token=" + access_token;
        JSONObject params = new JSONObject();
        params.put("proofCode", projectCode);     //项目编码
        params.put("version", "2");     //数值为1或者空，返回1.0版本json数据 2：返回2.0标准json数据，建议传2，1.0版本以后会撤销。
        params.put("type", "1");    //查询方式，取值： 1：只返回项目状态 2：验证项目编码，如果有效，返回项目基本信息
        String responseString = HttpClient.doPostJson(url, params.toString());
//        {"result":"{\"result\":2}","error":"","code":"200"}
        JSONObject jsonObject = JSONObject.fromObject(responseString);
        if ("200".equals(jsonObject.get("code")) && jsonObject!=null){
            msg = JSONObject.fromObject(jsonObject.get("result")).getString("result");
            if ("1".equals(msg) || "2".equals(msg)) {
                msg = "200";  //核验成功
            }
        } else {
            msg = "1001";  //失败
        }
        return msg;
    }

    /**
     * 材料附件上传网盘
     * @param folderNme  文件名称
     * @param filePath   文件路径
     * @return   docid   文件的uuid（十六进制）（成功）
     */
    private static String fileUploadDisk(String folderNme, String filePath){
        try {
            //请求 url
            String url = "http://10.194.252.56:8083/WebDiskServerDemo/upload";

            // keyValues 保存普通参数
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("uid", "440113000000"); //440113000000   用户的uid（使用部门编码），用于网盘逻辑区分文件所属，同时为该uid创建个人网盘，拥有空间大小限制
            keyValues.put("type", "doc");  //  存储类型，固定填doc
            keyValues.put("folder_name", folderNme);  //存放文件的文件夹名称，（使用部门名称）

            // filePathMap 保存文件类型的参数名和文件路径
            Map<String, String> filePathMap = new HashMap<>();
//        String filePath = "c:\\user_form\\1353523557128056832\\1353523557128056832_建设用地规划许可证和国有建设用地使用权不动产权证书合并办理.docx";
            if (!new File(filePath).exists()){
                System.out.println("找不到指定的路径----java.io.FileNotFoundException:  " + filePath);
                return "找不到指定的路径----java.io.FileNotFoundException:  ";
            }
            filePathMap.put("file", filePath);

            //headers
            Map<String, Object> headers = new HashMap<>();
            //COOKIE: Name=Value;Name2=Value2
            headers.put("COOKIE", "token=" + System.currentTimeMillis());
            String responseString =  HttpUtils.postFormData(url, filePathMap, keyValues, headers);
            System.out.println("======材料推送网盘返回responseString======" + responseString);
            //{"code":"0000","docid":11155599,"msg":"OK","uuid":"5099a28c-6112-11eb-aedc-d00d80981a7e"
            JSONObject responsObject = JSONObject.fromObject(responseString);
            if (responsObject!=null && "0000".equals(responsObject.get("code"))){
                return responsObject.getString("docid");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  根据事项编码/情形（办理项）编码获取申请材料信息
     *    [situation_code 及code和service_code不能同时为空，也不能同时传值；]
     * @param code      事项实施编码
     * @param service   事项内部编码
     * @param situationCode     情形（办理项）编码
     * @param is_nec   是否必要
     * @return    材料列表
     */
    @Override
    public String getMaterialsList(String code, String service, String situationCode, String is_nec) {
        String token = logonApi2();
        String url = "http://api2.gzonline.gov.cn:9090/api/eshore/two/power/getApplyList?access_token=" + token;  //正式环境
        JSONObject params = new JSONObject();
        params.put("code", code==null?"":code);   // 114401134554137174444TC12049000
        params.put("service", service==null?"":service);
        params.put("situation_code", situationCode==null?"":situationCode);
        try {
            String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
            JSONObject jsonObject = JSONObject.fromObject(responseText);
            System.out.println("---responseText----"+ jsonObject);
//        {"state":1,"data":[{"materials_id":"4fc65616b78b4c1897be2ddc2acc2f6f","inner_code":"6f6233b665f54a5994239828449a3f6b","materials_code":null,"materials_name":"立项用地规划许可阶段申请表","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":null,"sample_code":null,"empty_code":null,"sample_file":[{"file_id":"af45e0e13f25437b828c1d9b62f5df2f","file_name":"立项用地规划许可阶段申请表（填写样例）.doc","file_path":null}],"empty_file":[{"file_id":"9a3b9da962044e14a8ac497c0777c741","file_name":"立项用地规划许可阶段申请表（空表）.doc","file_path":null}],"sort_order":"1","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":null,"material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":null,"electronic_license_code":null,"standard_material":[],"is_relate_license":null,"clfl":null,"submission_required":null,"submission_measure":null,"lawlist":[]},{"materials_id":"7e3b1da2372342eb9b273b7e397b9f54","inner_code":"08957e10c1f0468fa8a4f682512cc05f","materials_code":null,"materials_name":"申请人身份证明","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"1","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"公安机关","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"居民身份证","electronic_license_code":"100208101","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"1","submission_measure":"3,3","lawlist":[]},{"materials_id":"96a50039ea8e404296c987d8e9f0d339","inner_code":"22e9fc1c41d94c77bdebc8ec53caa0ba","materials_code":null,"materials_name":"不动产登记申请表","copy":0,"filling_requirement":null,"standard_collection":"无","origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":null,"sample_code":null,"empty_code":null,"sample_file":[{"file_id":"5ed8597e85b042eca8ea1d8d9df4110b","file_name":"不动产登记申请表.doc","file_path":null}],"empty_file":[{"file_id":"50ba1bb1049448c3b6bdb72fa972a268","file_name":"不动产登记申请表.doc","file_path":null}],"sort_order":"2","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"无","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":null,"electronic_license_code":null,"standard_material":[],"is_relate_license":null,"clfl":null,"submission_required":null,"submission_measure":null,"lawlist":[]},{"materials_id":"af2c630589f3426fa53e7bddda4d896b","inner_code":"8e9078ac114944ed8c6eace8877acf2f","materials_code":null,"materials_name":"成交确认书","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"2","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"1","submission_measure":"4","lawlist":[]},{"materials_id":"c5e4e5ebdb3d4a0ab5a7a7fd14af0e62","inner_code":"226887ae31ab44c79a92d2e89ba77921","materials_code":null,"materials_name":"现状建筑物资料","copy":0,"filling_requirement":"新办《建设用地规划许可证》","standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"3","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"1","submission_measure":"4","lawlist":[]},{"materials_id":"504a989c019b4e14a6a57edb593e0906","inner_code":"f49774c80d8c428d84fdfb0d393e88e3","materials_code":null,"materials_name":"闲置土地处置意见","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"4","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"自然资源主管部门","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"a3d09b5033544ae48eec8b40c6eb6f1f","inner_code":"bc155a966b474ac89df0a52b62b247b9","materials_code":null,"materials_name":"土地权属来源材料","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"5","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"3f000f05d4254f2b8ff23e6df7ca13a5","inner_code":"6c0cc56828744992a3bbf65747f9c770","materials_code":null,"materials_name":"不动产权籍调查表、宗地图、宗地界址点坐标等不动产权籍调查成果","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"6","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"99","source_explain":"相关部门出具","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"de2eea80bd02471ea287d90579a08774","inner_code":"757e76ba31ba450d8adeace90d7c757e","materials_code":null,"materials_name":"更名后的《房地产权证》或《不动产权证》或批准更名的文件","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"7","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"国土资源部门","page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"中华人民共和国不动产权证书","electronic_license_code":"100169501","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"1","submission_measure":"4,3","lawlist":[]},{"materials_id":"f31d7519785545c2ae9648353fda91be","inner_code":"15353173a68b4877af898ed6bfd26ef2","materials_code":null,"materials_name":"土地出让价款、土地租金、税费等缴纳凭证；","copy":0,"filling_requirement":"2015年1月1日后广东省内（不含深圳）开具的各类税收票证，无需提交纸质件。","standard_collection":"无","origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"8","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"相关政府部门核发","page_format":"无","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"税收完税证明（文书式）","electronic_license_code":"200087301","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"0","submission_measure":null,"lawlist":[]}],"total":10,"message":"接口调用成功"}

            if ("1".equals(jsonObject.getString("state")) && jsonObject!=null){
                //如果is_nec不为空，则筛选出必要的材料
                if (!is_nec.isEmpty() && is_nec!=null){
                    JSONArray array = new JSONArray();
                    JSONObject dataObject = null;
                    JSONArray dataArray = JSONArray.fromObject(jsonObject.get("data"));
                    for (int i = 0; i< dataArray.size(); i++){
                        dataObject = dataArray.getJSONObject(i);
                        if ("1".equals(dataObject.get("material_necessity"))){  //必要材料
                            array.add(dataObject);
                        }
                    }
                    System.out.println("---------必要材料 array-----------------" + array.toString());
                    return array.toString();
                } else {
                    return jsonObject.getString("data");
                }
//                return jsonObject.get("data");
            } else {
                //返回查询错误信息
                return jsonObject.getString("message");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  根据事项编码/情形（办理项）编码获取事项/情形（办理项）详细信息
     *    [situation_code 及code和service_code不能同时为空，也不能同时传值；]
     * @param code      事项实施编码
     * @param service   事项内部编码
     * @param situationCode     情形（办理项）编码
     * @return    ServiceItem
     */
    @Override
    public String getItemList(String code, String service, String situationCode) {
        String token = logonApi2();
        String url = "http://api2.gzonline.gov.cn:9090/api/eshore/two/power/getItem?access_token=" + token;  //正式环境
        JSONObject params = new JSONObject();
        params.put("code", code==null?"":code);   // 114401134554137174444TC12049000
        params.put("service", service==null?"":service);
        params.put("situation_code", situationCode==null?"":situationCode);
        try {
            String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
            JSONObject jsonObject = JSONObject.fromObject(responseText);
//            System.out.println("---responseText----"+ jsonObject);
//            System.out.println("---responseText_Data----"+ jsonObject.get("data"));
//            {"service_code":"114401134554137174444TC12049000","ctg_code":"44TC12049000","name":"我要办理用地证（社会投资）","service_item_type":"TC","base_code":"44TC12049000","carry_out_code":"114401134554137174444TC12049000","handle_type":"2","state":"3","version":2,"service_agent_code":"455413717","service_agent_name":"广州市规划和自然资源局番禺区分局","service_agent_type":"1","region_code":"440113000000","region_name":"番禺区","accept_time":"&&","gjjblsx":null,"sjblsx":null,"shijblsx":null,"xjblsx":null,"zjjblsx":null,"wsyy":"1-http://wsbs.gz.gov.cn/gz/wsbs/yyzx.jsp","syxt":null,"sfjr":null,"jrfs":null,"sfzwtxs":null,"service_object":null,"service_object_type":"1,2,3,6,9","service_catalogs":[{"service_code":"001001065","name":null,"parent_code":null},{"service_code":"002001045","name":null,"parent_code":null}],"conditions":"以公开出让方式使用政府储备用地，已按《国有建设用地使用权出让合同》约定缴清国有建设用地使用权出让价款（含利息和违约金）和契税等相关税费的，实行建设用地规划许可证和国有建设用地使用权不动产权证书合并办理。","legal_basis":[{"basis_code":"4c3dccb0a63347a0aacd51d5615806cc","name":"不动产登记操作规范（试行）","wh":"国土资规〔2016〕6号","law_term":null,"office":"国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"85d70dcf26a048768d9b5e3acd668183","name":"中华人民共和国物权法（2007年）","wh":"中华人民共和国主席令第62号","law_term":null,"office":"全国人民代表大会","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"1ed43b551d4842e690fd23c5a5f5e8ac","name":"不动产登记暂行条例","wh":"中华人民共和国国务院令第656号","law_term":null,"office":"国务院","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"95cd09285906433b8ce4188bec5cb07a","name":"《广东省城乡规划条例》（2012年）","wh":"广东省第十一届人民代表大会常务委员会公告（第90号）","law_term":null,"office":"广东省人民代表大会常务委员会","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"4c3dccb0a63347a0aacd51d5615806cc","name":"不动产登记操作规范（试行）","wh":"国土资规〔2016〕6号","law_term":null,"office":"国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"23e598b5d3eb46eab18928c399dbf6a2","name":"《中华人民共和国城乡规划法》（2007年）","wh":"第十届全国人民代表大会常务委员会第三十次会议","law_term":null,"office":"全国人民代表大会常务委员会","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"85d70dcf26a048768d9b5e3acd668183","name":"中华人民共和国物权法（2007年）","wh":"中华人民共和国主席令第62号","law_term":null,"office":"全国人民代表大会","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"85d70dcf26a048768d9b5e3acd668183","name":"中华人民共和国物权法（2007年）","wh":"中华人民共和国主席令第62号","law_term":null,"office":"全国人民代表大会","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""},{"basis_code":"db35264c04fb4242b6abfdbd1697289e","name":"不动产登记暂行条例实施细则","wh":"国土资源部令[2016]第63号","law_term":null,"office":"中华人民共和国国土资源部","law_type":null,"orgCode":null,"carry_out_date":null,"expire_date":null,"basis_type":"1","file_path_type":null,"net_addr":null,"filePath":null,"fileName":null,"sort_order":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":1610726400000,"accordIngNumber":""}],"approval_conditions":[],"legal_period":40,"legal_period_type":"1","promised_period":5,"promised_period_type":"1","apply_time":null,"accept_time_sp":"自受理之日起，在承诺时限内办结","sscj":null,"windows":[{"window_code":"085959e6-5554-42fa-a914-31ffbff82036","window_num":null,"name":"广州市规划和自然资源局番禺区分局","floor":null,"complaint_line":null,"phone":"020-84690912","address":"广州市番禺区亚运大道550号番禺区政务服务中心三楼建设工程大厅C58-C60号窗口","office_hour":"星期一至星期五上午9：00-12：00，下午1：00-5：00（国家法定节假日按有关规定执行）","traffic_guide":"1. 短线及专线。短线：番微公交3路（区政府西门（会议中心站）—新政务服务中心—东湖洲花园站）；专线：番微公交5路（区政府西门—区政府东门—新政务服务中心）。 2.途径新政务中心公交线路（27条）：番1路、番6路、番10路、番16路、番19路、番26路、番29路、番51B路、番68路、番93路、番99路、番100路、番103路、番126路、番140路、番148路、番149路、番151B路、番153路、番160路、番161路、番162路，301路，广番97路、广番97短线，南沙54路、南沙66路。番禺汽车客运站[公交车站]下车步行至番禺区政务服务中心。","org_code":"455413717","org_name":"广州市规划和自然资源局番禺区分局","description":null,"creator":null,"creation_time":null,"last_modificator":null,"last_modification_time":null,"fileid":null,"topwin":null,"topwinText":null,"division_code":"440113000000","maplinks":null}],"have_certificate":"1","certificate":[{"item_oper_id":null,"name":"建设用地规划许可证","have_date":null,"law":null,"delay":null,"sample_file":[{"file_id":"5865f22c1d094c15908a5a32ccfe1cfb","file_name":"建设用地规划许可证.zip","file_path":null}],"sortorder":"1","subject_result_type":"10","short_name":null,"holder_type":"3","issue_level":"CITY,COUNTRY,TOWN","result_blank":[{"file_id":"27b585da677646cfad8bc1676d0f2942","file_name":"建设用地规划许可证.zip","file_path":null}],"is_relate_license":"1","license_code":"102018701","license_name":"建设用地规划许可证"},{"item_oper_id":null,"name":"中华人民共和国不动产权证书","have_date":"居住用地70年；工业用地50年；商业、旅游、娱乐用地40年；教育、科技、文化、卫生、体育用地50年；综合或者其他用地50年。","law":null,"delay":null,"sample_file":[{"file_id":"51af7204470a4c018a2ff4d05d9f803c","file_name":"证书证明.jpg","file_path":null}],"sortorder":"2","subject_result_type":"30","short_name":"不动产权证","holder_type":null,"issue_level":"CITY,COUNTRY","result_blank":[{"file_id":"32d40b6e0aa3478caaaf8fd21304cb4a","file_name":"证书证明.jpg","file_path":null}],"is_relate_license":"1","license_code":"100169501","license_name":"中华人民共和国不动产权证书"}],"need_charge":"0","charge":null,"submit_documents":[{"materials_id":"4fc65616b78b4c1897be2ddc2acc2f6f","inner_code":"6f6233b665f54a5994239828449a3f6b","materials_code":null,"materials_name":"立项用地规划许可阶段申请表","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":null,"sample_code":null,"empty_code":null,"sample_file":[{"file_id":"af45e0e13f25437b828c1d9b62f5df2f","file_name":"立项用地规划许可阶段申请表（填写样例）.doc","file_path":null}],"empty_file":[{"file_id":"9a3b9da962044e14a8ac497c0777c741","file_name":"立项用地规划许可阶段申请表（空表）.doc","file_path":null}],"sort_order":"1","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":null,"material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":null,"electronic_license_code":null,"standard_material":[],"is_relate_license":null,"clfl":null,"submission_required":null,"submission_measure":null,"lawlist":[]},{"materials_id":"7e3b1da2372342eb9b273b7e397b9f54","inner_code":"08957e10c1f0468fa8a4f682512cc05f","materials_code":null,"materials_name":"申请人身份证明","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"1","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"公安机关","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"居民身份证","electronic_license_code":"100208101","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"1","submission_measure":"3,3","lawlist":[]},{"materials_id":"96a50039ea8e404296c987d8e9f0d339","inner_code":"22e9fc1c41d94c77bdebc8ec53caa0ba","materials_code":null,"materials_name":"不动产登记申请表","copy":0,"filling_requirement":null,"standard_collection":"无","origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":null,"sample_code":null,"empty_code":null,"sample_file":[{"file_id":"5ed8597e85b042eca8ea1d8d9df4110b","file_name":"不动产登记申请表.doc","file_path":null}],"empty_file":[{"file_id":"50ba1bb1049448c3b6bdb72fa972a268","file_name":"不动产登记申请表.doc","file_path":null}],"sort_order":"2","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"无","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":null,"electronic_license_code":null,"standard_material":[],"is_relate_license":null,"clfl":null,"submission_required":null,"submission_measure":null,"lawlist":[]},{"materials_id":"af2c630589f3426fa53e7bddda4d896b","inner_code":"8e9078ac114944ed8c6eace8877acf2f","materials_code":null,"materials_name":"成交确认书","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"2","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"1","submission_measure":"4","lawlist":[]},{"materials_id":"c5e4e5ebdb3d4a0ab5a7a7fd14af0e62","inner_code":"226887ae31ab44c79a92d2e89ba77921","materials_code":null,"materials_name":"现状建筑物资料","copy":0,"filling_requirement":"新办《建设用地规划许可证》","standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"3","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"1","submission_measure":"4","lawlist":[]},{"materials_id":"504a989c019b4e14a6a57edb593e0906","inner_code":"f49774c80d8c428d84fdfb0d393e88e3","materials_code":null,"materials_name":"闲置土地处置意见","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"4","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"自然资源主管部门","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"a3d09b5033544ae48eec8b40c6eb6f1f","inner_code":"bc155a966b474ac89df0a52b62b247b9","materials_code":null,"materials_name":"土地权属来源材料","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"5","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"10","source_explain":null,"page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"3f000f05d4254f2b8ff23e6df7ca13a5","inner_code":"6c0cc56828744992a3bbf65747f9c770","materials_code":null,"materials_name":"不动产权籍调查表、宗地图、宗地界址点坐标等不动产权籍调查成果","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"03","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"6","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"99","source_explain":"相关部门出具","page_format":"A4","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"","electronic_license_code":"","standard_material":[],"is_relate_license":"0","clfl":"03","submission_required":"0","submission_measure":null,"lawlist":[]},{"materials_id":"de2eea80bd02471ea287d90579a08774","inner_code":"757e76ba31ba450d8adeace90d7c757e","materials_code":null,"materials_name":"更名后的《房地产权证》或《不动产权证》或批准更名的文件","copy":0,"filling_requirement":null,"standard_collection":null,"origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"7","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"国土资源部门","page_format":"A4","material_form":"1,2","material_necessity":"2","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"中华人民共和国不动产权证书","electronic_license_code":"100169501","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"1","submission_measure":"4,3","lawlist":[]},{"materials_id":"f31d7519785545c2ae9648353fda91be","inner_code":"15353173a68b4877af898ed6bfd26ef2","materials_code":null,"materials_name":"土地出让价款、土地租金、税费等缴纳凭证；","copy":0,"filling_requirement":"2015年1月1日后广东省内（不含深圳）开具的各类税收票证，无需提交纸质件。","standard_collection":"无","origin":1,"elect":0,"origin_accept_num":0,"origin_check_num":0,"copy_accept_num":0,"copy_check_num":0,"materials_type":"01","sample_code":null,"empty_code":null,"sample_file":[],"empty_file":[],"sort_order":"8","remark":null,"creator":null,"creation_time":1609431501000,"last_modificator":null,"last_modification_time":null,"material_desc":null,"sources_type":"20","source_explain":"相关政府部门核发","page_format":"无","material_form":"1,2","material_necessity":"1","intermediary_services":null,"intermediary_services_code":null,"electronic_license":"税收完税证明（文书式）","electronic_license_code":"200087301","standard_material":[],"is_relate_license":"1","clfl":"01","submission_required":"0","submission_measure":null,"lawlist":[]}],"situation":[],"window_process":null,"suit_online":"0","online_service_url":null,"transport_count":"0","declare_service_level":"1","online_reason":null,"ems":"0,1","digital_certificate":"0","online_charge":"0","invest":"0","pre_approval":"1","pre_approval_item":[{"service_code":"114401134554137174444TC12049000","pre_city_code":null,"front_service_code":null,"situation":null,"remark":null,"qzxksx":"","qzxkyj":[]}],"pro_intermediary":"0","sort_order":null,"faq":null,"creator":null,"creation_time":1603761667000,"last_modificator":null,"last_modification_time":1609406117000,"apply_content":null,"is_single_organ_approve":null,"single_approve_organ":null,"is_cross_organ_approve":null,"cross_approve_organ":null,"serviceAgency":{"serviceName":null,"chooseServiceWay":null,"serviceTime":null,"lawList":null,"projectList":null},"serviceApprovalConditionsLimit":{"isNumLimit":"0","numLimitRemark":"","isTechnologyLimit":"0","technologyContentList":[]},"serviceCheckConsult":{"duty_permission":null,"work_process":null,"question":null,"address":"","phone_number":"020-84690912","net_address":"","weibo_address":"","wechat":"","email":"","mail_address":"","mail_code":"","reply_time":"","reply_region":""},"serviceCheckWay":{"is_smjc":"0","is_sdjc":"0","is_dqjy":"0","is_cyjc":"0","is_njnb":"0","is_fljg":"0","is_cxda":"0","is_tsjb":"0","smjc_gd":null,"smjc_gd_name":null,"sdjc_gd":null,"sdjc_gd_name":null,"dqjy_gd":null,"dqjy_gd_name":null,"cyjc_gd":null,"cyjc_gd_name":null,"njnb_gd":null,"njnb_gd_name":null,"fljg_gd":null,"fljg_gd_name":null,"cxda_gd":null,"cxda_gd_name":null,"tsjb_gd":null,"tsjb_gd_name":null,"smjc_lh":null,"smjc_lh_name":null,"sdjc_lh":null,"sdjc_lh_name":null,"dqjy_lh":null,"dqjy_lh_name":null,"cyjc_lh":null,"cyjc_lh_name":null,"njnb_lh":null,"njnb_lh_name":null,"fljg_lh":null,"fljg_lh_name":null,"jdjcfs":null},"serviceDuty":[{"type":"1","text":"无","sort":"1"}],"serviceHallSituation":{"is_seated_executive_halls":"1","is_seated_profe_service_hall":"0","unseated_remark":null,"seated_remark":null,"is_city_suit":"0","city_remark":null,"is_relegate":"0","relegate_remark":null,"unrelegate_reason":null},"serviceInnerApproval":{"file_id":"","file_name":"","file_path":"","remark":null},"serviceRight":[{"type":"0","text":"无","sort":"1"}],"serviceLawHelp":{"window_complain":"","phone_complain":"020-12345","net_complain":null,"email_complain":null,"mail_complain":null,"complain_reply":null,"fy_region":"广州市番禺区人民政府","fy_address":"广州市番禺区人民政府主楼东903室","fy_phone":"020-84636756","fy_net":"http://www.panyu.gov.cn/gzpy/wssq/common_tt.shtml?tdsourcetag=s_pcqq_aiomsg","ss_region":"广州铁路运输法院","ss_address":"广州市荔湾区花地大道中68号","ss_phone":"020-37890836","ss_net":"http://www.gtyy.gov.cn/"},"serviceSystemType":{"system_type":null,"declare_system_name":null,"declare_system_level":null,"declare_system_url":null,"approval_system_name":null,"approval_system_leve":null,"approval_system_url":null,"is_validate":"0","is_foreign":null},"comApprSpecialOnePlatForm":null,"comApprNodeOnePlatForm":{"id":"410cb3ecebf646c1a1d1baf6af76503d","itemOperId":"54ad7bebc76742bea2b990f716db9d84","qhOrgCode":null,"qhHandler":null,"qhHandleResult":null,"qhTimeLimit":null,"qhHandleRequireName":null,"qhHandleRequirePath":null,"yyOrgCode":null,"yyHandler":null,"yyHandleResult":null,"yyTimeLimit":null,"yyHandleRequireName":null,"yyHandleRequirePath":null,"sqjsOrgCode":null,"sqjsHandler":null,"sqjsHandleResult":null,"sqjsTimeLimit":null,"sqjsHandleRequireName":null,"sqjsHandleRequirePath":null,"djOrgCode":null,"djHandler":null,"djHandleResult":null,"djTimeLimit":null,"djHandleRequireName":null,"djHandleRequirePath":null,"sqbhOrgCode":null,"sqbhHandler":null,"sqbhHandleResult":null,"sqbhTimeLimit":null,"sqbhHandleRequireName":null,"sqbhHandleRequirePath":null,"sjpzOrgCode":null,"sjpzHandler":null,"sjpzHandleResult":null,"sjpzTimeLimit":null,"sjpzHandleRequireName":null,"sjpzHandleRequirePath":null,"slshOrgCode":null,"slshHandler":null,"slshHandleResult":null,"slshTimeLimit":null,"slshHandleRequireName":null,"slshHandleRequirePath":null,"bzclOrgCode":null,"bzclHandler":null,"bzclHandleResult":null,"bzclTimeLimit":null,"bzclHandleRequireName":null,"bzclHandleRequirePath":null,"sljdOrgCode":null,"sljdHandler":null,"sljdHandleResult":null,"sljdTimeLimit":null,"sljdHandleRequireName":null,"sljdHandleRequirePath":null,"scfsOrgCode":null,"scfsHandler":null,"scfsHandleResult":null,"scfsTimeLimit":null,"scfsHandleRequireName":null,"scfsHandleRequirePath":null,"sjzbOrgCode":null,"sjzbHandler":null,"sjzbHandleResult":null,"sjzbTimeLimit":null,"sjzbHandleRequireName":null,"sjzbHandleRequirePath":null,"specialReview":"0","spscOneNodeName":null,"spscOneOrgCode":null,"spscOneHandler":null,"spscOneHandleResult":null,"spscOneTimeLimit":null,"spscOneHandleRequireName":null,"spscOneHandleRequirePath":null,"spscTwoNodeName":null,"spscTwoOrgCode":null,"spscTwoHandler":null,"spscTwoHandleResult":null,"spscTwoTimeLimit":null,"spscTwoHandleRequireName":null,"spscTwoHandleRequirePath":null,"spscThreeNodeName":null,"spscThreeOrgCode":null,"spscThreeHandler":null,"spscThreeHandleResult":null,"spscThreeTimeLimit":null,"spscThreeHandleRequireName":null,"spscThreeHandleRequirePath":null,"jdOrgCode":null,"jdHandler":null,"jdHandleResult":null,"jdTimeLimit":null,"jdHandleRequireName":null,"jdHandleRequirePath":null,"zjzzOrgCode":null,"zjzzHandler":null,"zjzzHandleResult":null,"zjzzTimeLimit":null,"zjzzHandleRequireName":null,"zjzzHandleRequirePath":null,"zjsdOrgCode":null,"zjsdHandler":null,"zjsdHandleResult":null,"zjsdTimeLimit":null,"zjsdHandleRequireName":null,"zjsdHandleRequirePath":null,"gdOrgCode":null,"gdHandler":null,"gdHandleResult":null,"gdTimeLimit":null,"gdHandleRequireName":null,"gdHandleRequirePath":null,"jdgkOrgCode":null,"jdgkHandler":null,"jdgkHandleResult":null,"jdgkTimeLimit":null,"jdgkHandleRequireName":null,"jdgkHandleRequirePath":null,"middleBasis":null,"middleSituationName":null,"middleImplementProcess":null,"finalBasis":null,"finalSituationName":null,"finalImplementProcess":null,"isContainQh":null,"isContainYy":null,"isContainDj":null,"isContainSqbh":null,"isContainSjzb":null,"isContainZjzzYsd":null,"specialReviewType":null,"slshSendWay":null,"spscOneSendWay":null,"jdSendWay":null,"zjzzSendWay":null,"zjsdSendWay":null,"sqjsSendWay":null},"itemQa":[{"rowguId":"e2559b862739478eb2b8f4211efec0fb","itemguId":"54ad7bebc76742bea2b990f716db9d84","question":"建设用地规划许可证和国有建设用地使用权不动产权证书合并办理流程？","answer":"答：1.前期准备阶段： （1）公开出让地块在出让前委托有资质的测绘单位制作规划条件附图、土地出让合同附图、建设用地批准书附图和不动产权证附图，完成权籍调查； （2）竞得人与广州公共资源交易中心签订《成交确认书》、与我委签订《国有建设用地使用权出让合同》并明确土地出让起始时间； （3）《成交确认书》约定拟成立项目公司的，竞得人、项目公司与我委签定《国有建设用地使用权出让合同》变更协议； （4）竞得人或项目公司按《国有建设用地使用权出让合同》约定缴交国有建设用地使用权出让价款、利息和违约金； （5）竞得人或项目公司向税务部门缴交契税。 2.三证合办阶段： （1）竞得人或项目公司向我委提出合并办理建设用地规划许可证、建设用地批准书和国有建设用地使用权首次登记申请； （2）窗口受理和案件交接； （3）案件办理。 收到系统和纸质案件后，通过信息共享调阅建设用地批准书附图成果，并于4个工作日内完成建设用地规划许可证和建设用地批准书审核工作。通过信息共享调阅国有建设用地使用权出让合同、不动产权证附图和权籍调查成果，4个工作日内同步完成国有建设用地使用权首次登记审核工作。 （4）出文制证、核准登记并缮证。 建设用地规划许可证和建设用地批准书经领导签发后，由市或区政务窗口0.5个工作日内出文制证、用印。国有建设用地使用权首次登记经领导签发后，由市或区不动产登记中心0.5个工作日内核准登记、缮证并交换到市或区政务窗口。政务窗口收齐建设用地规划许可证、建设用地批准书和国有建设用地使用权首次登记审批结果后立即移交市或区政务中心出件窗口。 （5）出件窗口发证。 申请人选择“直接到窗口领取”和“邮政速递”的，由市或区政务中心出件窗口向申请人发出或邮政速递建设用地规划许可证、建设用地批准书和国有建设用地使用权首次登记审批结果。申请人选择“自助取件柜领取”的，由具体经办人持身份证（含市民卡）原件和取证密码在规定时间内到自动取件密码箱自行领取。","orderNum":1}],"serviceType":"","handleArea":"9","wtbm":"","slsxsm":"","isXysfyz":"0","sqsxdw":null,"itemId":"114401134554137174444TC12049000","itemsource":"1","deptCode":"1","entrustName":"","effectplan":null,"cancelplan":"","factdegreeExplain":"","levyKind":"","isLevywaiver":"","acceptType":"1","ckbllc":"1、申请，申请人按照办事指南，备齐申请材料，根据事权分工，到广州市政务服务中心窗口或各区规划和自然资源局窗口或者区政务服务中心综合受理窗口（已实行集成服务的区局）提出申请。\n2、受理，窗口工作人员经审查符合申请条件，予以受理。\n3、审查，窗口工作人员将案件送交内部办理部门进行办理，符合审批条件的，提出准予许可的拟办意见；不符合审批条件的，提出不准予许可的拟办意见;\n4、决定，办理的处（科）室将案件呈批给具有审批权的领导审定签发。\n5、出文制证，领导审定后，办理部门将案件移送窗口部门出文制证或由办理部门出文制证。;6、文书送达，根据申请人选择的送达方式由申请人自行领取或邮件送达。","wsbllc":"","webTaskcodeParam":"","webTaskversionParam":"","webOldbmParam":"","webOldbbParam":"","fdblsxsm":"自受理之日起，在法定时限内办结","publishdate":"2020-12-31 17:15:17","year":"","cjsqdm":"","cjsqmc":"","xzjddm":"","xzjdmc":"","fzcd":"","fqfx":"","isBfxf":"","wtfx":"","itemGuid":"54ad7bebc76742bea2b990f716db9d84","xsnr":"","sqsxsm":"","windowFile":{"file_id":"59e445a2d433434d996455b72ee38637","file_name":"用地证流程图.png","file_path":""},"webFile":null,"originType":"0","tongyi_code":"114401134554137174","is_express_delivery":null,"express_address":null,"tags":[],"par_task_name":null,"par_task_code":null,"par_dept_name":null,"par_tongyi_code":null,"nation_catalog_code":null,"nation_task_code":null,"situation_code":null,"shortname":null,"common_word":null,"is_yishenqing":null,"is_bianmin":null,"administration":"12","resp_office":null,"is_permission":null,"duty_subject":null,"scope_division":null,"tbxs":null,"is_bayarea_enable":"0","biz_cycle":null,"service_object_scope":null,"check_standard":null,"is_absence_accept":"0","is_trust_approval":"0","is_importonewindow":null,"is_importmultiwindow":"0","entry_center_code":null,"online_payment_way":null,"online_payment_note":null,"express_note":null,"can_self_terminal_apply":"0","can_self_terminal_apply_type":null,"is_notify_promise":"0","is_fast_approve":"0","is_finger":"0","finger_service_content":null,"finger_service_remake":null,"finger_service_type":null,"is_announced":null,"can_ca_login":"0","can_mobile_ca_login":"0","mobile_applt_website":null,"extend":null,"service_biz_system":[{"id":"e85a224c6dc04f98843c845c3c647c71","name":"广州市工程建设项目联合审批平台","register_org_name":null,"construction_org":"广州市番禺区住房和城乡建设局","system_type":"3","unify_apply_system":null,"businsesscopedesc":null,"system_deploy_address":null,"system_desc":null,"system_network_type":null,"businesswebsite":null,"developer_name":null}],"service_jointind_org":[],"link_way":"电话咨询","supervise_way":"电话投诉","agent_subject_code":null,"agent_subject_name":null,"situation_name":null,"useLevel":"4","theme_gr_type":null,"theme_fr_type":null}
            return responseText;
//            if ("1".equals(jsonObject.getString("state")) && jsonObject!=null){
//                return jsonObject.getString("data");
//            } else {
//                //返回查询错误信息
//                return jsonObject.getString("message");
//            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  根据材料编号和部门组织机构编码获取引用了该材料的事项信息
     * @param inner_code     材料内部编码
     * @param org_code       部门机构编码
     * @return    List<MaterialItemInfo>
     */
    @Override
    public String getMaterialItemInfoList(String innerCode, String orgCode) {
        String token = logonApi2();
        String url = "http://api2.gzonline.gov.cn:9090/api/eshore/two/power/getApplyCode?access_token=" + token;  //正式环境
        try {
            JSONObject params = new JSONObject();
            params.put("inner_code", innerCode);   // 3dcfce3612f126357c9cd27cfdfed605
            params.put("org_code", orgCode);       // MB2C92966
            String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
            JSONObject jsonObject = JSONObject.fromObject(responseText);
            System.out.println("---jsonObject----"+ jsonObject);
//            {"state":1,"data":[{"material_id":"39cf40db3b6d4f3bbdd1ac90b777808b","material_name":"《食品经营许可证》变更、延续申请书","service_code":"c2d768f5a360bf778bb5ec298bec7e12","service_name":"食品经营许可证变更"}],"total":1,"message":"接口调用成功"}

            if ("1".equals(jsonObject.getString("state")) && jsonObject!=null){
                return jsonObject.getString("data");
            } else {
                //返回查询错误信息
                return jsonObject.getString("message");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getOrgListEx() {
        String token = logonApi2();
        String url = "http://api2.gzonline.gov.cn:9090/api/eshore/two/OrganizationService/getOrgListEx?access_token=" + token;  //正式环境
        try {
            JSONObject params = new JSONObject();
            params.put("region_code", "440113000000");   // 440113000000   番禺区编码
            String responseText = HttpClient.doPostJson(url, JSONObject.fromObject(params).toString());
            System.out.println("---jsonObject----"+ responseText);
            return  responseText;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
