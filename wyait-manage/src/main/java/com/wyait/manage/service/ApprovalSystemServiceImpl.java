/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ApprovalSystemServiceImpl
 * Author:   Administrator
 * Date:     2021/1/14
 * History:
 */
package com.wyait.manage.service;

import com.wyait.manage.dao.SysIntfParameterDAO;
import com.wyait.manage.service.db1.ApprovalSystemService;
import com.wyait.manage.utils.Base64Util;
import com.wyait.manage.utils.HttpClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈审批系统业务类〉
 *
 * @author Administrator
 * @create 2020/12/28 13:52
 * @since 1.0.0
 */
@Service
public class ApprovalSystemServiceImpl implements ApprovalSystemService {

    @Autowired
    private SysIntfParameterDAO sysIntfParameterDAO;

    /**
     * 1. 启动办件接口，生成办件信息：
     *   5.2.1.42根据事项流水号，产生一条办件。
     * @return     controlSeq:—办件流水号 ，commonCode:—统一查询码
     */
    public String createCommom(){
        String url = "http://172.25.101.131:7001/CommonService/api/control/startControl/2/add.v"; //推送办件信息
        Map<String, String> params = new HashMap<>();
        params.put("userCode", "qiyetest");  // 用户登录名
        params.put("approveSeq", "17883");  //事项流水号
        String resultString = HttpClient.doPost(url, params);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
//        {"status":"200","desc":"处理成功","data":{"commonCode":"py3474059982012021000010","controlSeq":"2020173014"}}
        if (jsonObject.get("status") == "200" && jsonObject!=null){
            JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
            String commonCode = dataObject.get("commonCode").toString();
            String controlSeq = dataObject.getString("controlSeq");
        }
        return null;
    }

    /**
     * 2. 更新办件申请人信息
     *      5.2.1.5根据办件流水号，更新办件申请人信息
     */
    @Override
    public void updateInformation (String controlSeq){
        String url = "http://172.25.101.131:7001/CommonService/api/control/controlCust/update.v"; //更新办件信息
        Map<String, String> params = new HashMap<>();
        params.put("controlSeq", "2020173014");    //办件流水号
        params.put("custName", "企业测试名称");   //企业名称
        params.put("custContactPerson", "张华"); // 经办人名称
        params.put("custCardType","10");  //  申请人/经办人证件类型
        params.put("custCardId","440882199602151030");    //申请人/经办人证件号码

        params.put("custAddr","23254564");//申请人、经办人地址
        params.put("custLegalMan","张海楠");//  法定代表人姓名
        params.put("custCerType","10");//  法定代表人证件类型,企业用户
        params.put("custCerId","440882199602151030");//  法定代表人证件号码,企业用户
        params.put("mobilePhone","13244447856");//  申请人/经办人手机号码
        params.put("telphone","020-86475458");//  申请人/经办人固定电话
        params.put("regNum","1254884577");//  工商注册号
        params.put("orgCode","3464448454");//  组织机构代码
        params.put("taxpayer","83446623");//  税务登记号
        params.put("credNum","1267574323");//  统一社会信用代码
        params.put("prjName","开班便利店");//  项目名称(批量申请不支持)
        params.put("opertRange","建筑工程设计及技术咨询;企业管理咨询;房地产信息咨询;各类建筑工程项目管理及咨询;室内装潢设计及咨询。");//  项目经营范围
        params.put("oprtArea","40平方米");//  项目经营面积
        params.put("itemInvest","100");//  项目投资金额
        params.put("investorType","个人");//  项目投资来源
        params.put("prjAddress","广州番禺区市桥");//  项目地址
        params.put("buildingType","城乡住宅和公共设施用地");//  建设用地类型
        params.put("globalId","154346");//  项目统一编码
        params.put("manageItem","科技类 印务、船务、医疗器械、电脑领域内的技术开发、技术转让、技术咨询、技术服务等。    二、商贸型企业经营范围参考       1、百货（日用百货、服装服饰、鞋帽、皮革制品、玩具、洗涤用品、化妆品、护肤用品、摄影器材、音响设备及器材、体育用品等）； 2、文化办公用品（纸制品）、纸张、办公设备等 ；");//  经营范围
        params.put("custType2","e1"); //申办人企业类型: e1国有;e2私营;e3集体;e4股份制;e5中外合资（外资）;e7事业单位;e8机关单位;e6其他
        params.put("projType","2");//  项目类型，某些类型的项目对写值有要求，0企业投资类建设项目，1财政投资类建设项目，2个体工商，3其他
        params.put("currencyType","1");//  登记时企业注册资金的单位 1-万元 2-元 3-欧元 4-美元 5-港币 6-新加坡元 7-万美元
        String resultString = HttpClient.doPost(url,params);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
//        {"status":"200","desc":"处理成功","data":{"commonCode":"py3474059982012021000010","controlSeq":"2020173014"}}
//        System.out.println("22222----mian----" + resultString);
        if ("200".equals(jsonObject.get("status")) && jsonObject!=null){
//            JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
//            {"status":"200","desc":"处理成功","data":{"controlSeq":"2020173014"}}
        }
    }

    /**
     * 办件附件base64编码上传接口
     * @return
     */
    public String attachmentUpload(String path){
        String result = "";
        try {
//            String path = "c://theme_matter//docFile//材料清单_1606980649010.doc";
            File file = new File(path);
            String fileName = file.getName();
            String fileTyle = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());

//            System.out.println(file.getName());
            String ret = Base64Util.encodeBase64File(path);//C:\theme_matter\docFile
//            System.err.println("-----转换成base-64编码--------：" + ret);
            Map<String, String> params = new HashMap<>();
            params.put("controlSeq", "2020173014");    //办件流水号
            params.put("attachType", fileTyle);    //附件类型,如：jpg
            params.put("funcType", "extra");     //当不上传stuffId或者stuffCode时候为必转，传值为extra，表示作为额外材料附件上传
            params.put("func", "extra");      // 当不上传stuffId或者stuffCode时候为必转，传值为extra，表示作为额外材料附件上传
            params.put("attach", ret);   // 需要上传的附件,base64编码的字符串
            String url = "http://172.25.101.131:7001/CommonService/api/attach/base64/add.v"; //post请求
            String resultString = HttpClient.doPost(url, params);
//{"status":"200","desc":"处理成功","data":[{"status":"200","attachName":"20201730141607066045936.doc","desc":"上传成功","attachPath":"2020/12/04/2020173014/20201730141607066045936.doc","seq":"24912"}]}
            JSONObject jsonObject = JSONObject.fromObject(resultString);
            if ("200".equals(jsonObject.get("status")) && jsonObject!=null){
                result = JSONObject.fromObject(jsonObject.get("data")).toString();
            }
            //解码后保存位置
//            t.decoderBase64File(ret, "d://ghsTest/retFile.doc", "d://ghsTest/");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询办件附件列表接口
     * @return
     */
    public Object queryAdnexal(String controlSeq){
        String url = "http://172.25.101.131:7001/CommonService/api/attach/query.v"; //post请求
        Map<String, String> params = new HashMap<>();
        params.put("controlSeq", controlSeq);    //"2020173014"
        String resultString = HttpClient.doPost(url,params);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
//{"status":"200","desc":"处理成功","data":{"dataList":[{"controlSeq":"2020173014","attachName":"20201730141607059442740.doc","attachDesc":"C2ihfai5","attachSize":1,"attachState":-1,"attachCreator":"qiyetest","attachCreateTime":1607059442000,"stuffId":null,"attachBody":null,"seq":"24911","attachType":"doc","unionControlSeq":null,"attachPath":"2020/12/04/2020173014/20201730141607059442740.doc","licenseCode":null,"status":null,"extraAttach":"http://172.25.101.126/CommonService/api/attach/showExtraAttach/query.v?seq=24911","exchangeStatus":0},{"controlSeq":"2020173014","attachName":"20201730141607066045936.doc","attachDesc":"CWlaG5tO","attachSize":1,"attachState":-1,"attachCreator":"qiyetest","attachCreateTime":1607066045000,"stuffId":null,"attachBody":null,"seq":"24912","attachType":"doc","unionControlSeq":null,"attachPath":"2020/12/04/2020173014/20201730141607066045936.doc","licenseCode":null,"status":null,"extraAttach":"http://172.25.101.126/CommonService/api/attach/showExtraAttach/query.v?seq=24912","exchangeStatus":0},{"controlSeq":"2020173014","attachName":"20201730141606983527280.jpg","attachDesc":"1OR8b4Zv","attachSize":1,"attachState":-1,"attachCreator":"qiyetest","attachCreateTime":1606983527000,"stuffId":null,"attachBody":null,"seq":"24908","attachType":"jpg","unionControlSeq":null,"attachPath":"2020/12/03/2020173014/20201730141606983527280.jpg","licenseCode":null,"status":null,"extraAttach":"http://172.25.101.126/CommonService/api/attach/showExtraAttach/query.v?seq=24908","exchangeStatus":0},{"controlSeq":"2020173014","attachName":"20201730141607050899219.jpg","attachDesc":"IhOyKwsv","attachSize":1,"attachState":-1,"attachCreator":"qiyetest","attachCreateTime":1607050899000,"stuffId":null,"attachBody":null,"seq":"24909","attachType":"jpg","unionControlSeq":null,"attachPath":"2020/12/04/2020173014/20201730141607050899219.jpg","licenseCode":null,"status":null,"extraAttach":"http://172.25.101.126/CommonService/api/attach/showExtraAttach/query.v?seq=24909","exchangeStatus":0},{"controlSeq":"2020173014","attachName":"20201730141607052160452.jpg","attachDesc":"vGu1h6s4","attachSize":1,"attachState":-1,"attachCreator":"qiyetest","attachCreateTime":1607052160000,"stuffId":null,"attachBody":null,"seq":"24910","attachType":"jpg","unionControlSeq":null,"attachPath":"2020/12/04/2020173014/20201730141607052160452.jpg","licenseCode":null,"status":null,"extraAttach":"http://172.25.101.126/CommonService/api/attach/showExtraAttach/query.v?seq=24910","exchangeStatus":0}],"pageObj":{"pageRowNum":10,"currPage":1,"totalPage":1,"totalRow":5,"pageIndex":1}}}
        if ("200".equals(jsonObject.get("status")) && jsonObject!=null){
            JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
//            JSONArray dataList = JSONArray.fromObject(dataObject.get("dataList"));
            return dataObject.get("dataList");
        }
        return null;
    }

    /**
     * 办件附件删除接口
     * @return
     */
    public void deleteAdnexal(){
        String url = "http://ip:port/CommonService/api/attach/delete.v";
        Map<String, String> params = new HashMap<>();
        params.put("userCode", "qiyetest");
        params.put("approveSeq", "17883");
        String resultString = HttpClient.doPost(url,params);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        if ("200".equals(jsonObject.get("status")) && jsonObject!=null){
            JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
        }
    }
}
