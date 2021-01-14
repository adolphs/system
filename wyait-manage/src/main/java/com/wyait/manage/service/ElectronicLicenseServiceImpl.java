/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ElectronicLicenseServiceImpl
 * Author:   Administrator
 * Date:     2021/1/14
 * History:
 */
package com.wyait.manage.service;

import com.wyait.manage.dao.SysIntfParameterDAO;
import com.wyait.manage.service.db1.ElectronicLicenseService;
import com.wyait.manage.utils.Base64Util;
import com.wyait.manage.utils.HttpClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈电子证照系统接口业务类〉
 *
 * @author Administrator
 * @create 2020/12/28 13:56
 * @since 1.0.0
 */
@Service
public class ElectronicLicenseServiceImpl implements ElectronicLicenseService {

    @Autowired
    private SysIntfParameterDAO sysIntfParameterDAO;


    /**
     * 4.1.1登录
     * @return
     */
    @Override
    public String login() {
        /**
         * http://172.25.242.100:8009/license-app/v1/security/login
         */
        String url = "http://172.25.242.100:8009/license-app/v1/security/login";
//        String access_token = "";
        JSONObject json = new JSONObject();
        json.put("app_key", "przEvXSoppjUJEJ");
        json.put("app_secret", "CHxuGQdoMqlheSZ");
        json.put("account", "pyzsj_test");
        json.put("password", "jGgdSXgdArMwuJc");
        String resultString = HttpClient.deviceRequest(url, json.toString());
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        return jsonObject.get("access_token").toString();
    }

    /**
     * 4.1.2退出登录
     * @return
     */
    public static void logout(String params) {
        /**
         * http://172.25.242.100:8009/license-app/v1/security/logout
         */
        String url = "http://172.25.242.100:8009/license-app/v1/security/logout?access_token=";
//        String params = "7ba8fd06-d8fc-4922-9bd1-88709a39b012";
        HttpClient.sendGet(url, params);
    }

    /**
     * 4.1.3获取当前登录用户信息
     * @param accessToken
     * @return
     */
    public Map<String,Object> getCurrentUser(String accessToken){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String url = "http://172.25.242.100:8009/license-app/v1/security/get_current_user?access_token="; //   当前用户登录信息
        String resultString = HttpClient.sendGet(url, accessToken);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        if ("SUCCESS".equals(jsonObject.get("ack_code")) && jsonObject!=null){
            JSONObject data = JSONObject.fromObject(jsonObject.get("data"));
            resultMap.put("division",data.get("division"));      //非空判断
            resultMap.put("division_code", data.get("division_code"));
            data = JSONObject.fromObject(data.get("service_org"));
            resultMap.put("org_code",data.get("org_code"));
        }
        return resultMap;
    }

    /**
     * 4.3.1查询部门事项  【事项与证照关系接口】
     * @param accessToken    access_token, org_code
     * @return    返回事项编码   service_item_code
     */
    public Map<String, Object> serviceItem(String accessToken, String orgCode){
        Map<String, Object> resultMap = new HashMap<>();
        String url = "http://172.25.242.100:8009/license-app/v1/service_item/list?type=0&org_code="+ orgCode +"&access_token=";
        String resultString = HttpClient.sendGet(url, accessToken);
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        if ("SUCCESS".equals(jsonObject.get("ack_code")) && jsonObject!=null){
            JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
            resultMap.put("service_item_code", dataObject==null?0:dataObject.get("service_item_code"));
            resultMap.put("service_item_name",dataObject==null?0:dataObject.get("name"));
        }
        return  resultMap;
    }

    /**
     *  委托授权用证
     */
    public void queryAuthorization(String access_token, String userToken, String num, String serviceCode, String serviceName) {
        /**
         * https://{API_ROOT}/license/entrust?access_token= ACCESS_TOKEN
         */
        String url = "http://172.25.242.100:8009/license-app/v1/license/entrust";
        JSONObject json = new JSONObject();
        json.put("service_item_code", serviceCode);   //事项编码
        json.put("service_item_name", serviceName);     //事项名称
        json.put("biz_num", num);                       //对应办件的业务流水号
        json.put("credential", userToken);              //受委托人实名登录凭据 token
        json.put("access_token", access_token);
//        com.wyait.manage.utils.HttpClient.deviceRequest(url,json.toString());
        String resultString = HttpClient.sendGet(url, access_token);  //电子证照摘要列表
    }

    /**
     * 4.7.2 网上申办用证
     *   【列举一个持证人所有的电子证照清单，同时对结果清单每一项生成用证码。】
     */
    public Map<String, Object> queryLicenceCode(String access_token, String serviceCode, String serviceName, String authToken) {
        Map<String, Object> params = new HashMap<>();
        /**
         * https://{API_ROOT}/license/holder
         */
        String url = "http://10.194.212.27:8081/license-app/v1/license/holder?access_token=" + access_token;
        JSONObject ramas = new JSONObject();
        ramas.put("service_item_code", serviceCode);   //事项编码
        ramas.put("service_item_name", serviceName);     //事项名称
        ramas.put("biz_num", "");                       //对应办件的业务流水号
        ramas.put("credential", authToken);              //受委托人实名登录凭据 token
        ramas.put("cas_no", "001");       //实名认证来源的类型。详见广东省电子证照系统数据标准

        String resultString = HttpClient.doPostJson(url, ramas.toString());  //获取证照code
        JSONObject responseText = JSONObject.fromObject(resultString);
//        System.out.println("-----------4.7.2 网上申办用证-----responseText--------------------" + responseText);
        if ("SUCCESS".equals(responseText.get("ack_code")) && responseText!=null){
            params.put("content" ,responseText.get("data"));
        } else {
            // FAILURE   返回错误
            params.put("content" ,responseText.get("errors"));
        }
        return params;
    }


    /**
     * 4.4.2 创建制证数据
     *      【创建电子证照制证数据。】
     */
    public String createCertificate (String itemCode, String accessToken){
        String msg = null;
        try {
            String url = "http://10.194.212.27:8081/license-app/v1/license/" + itemCode + "/create?access_token=" + accessToken;
            JSONObject parames = new JSONObject();
            parames.put("service_item_code", "py146103001");   //事项编码
            parames.put("service_item_name", "住所（经营场所）场地使用证明");     //事项名称
            parames.put("biz_num", "20202255062");                       //对应办件的业务流水号
            parames.put("license_group", "非住改商");
            parames.put("seal_code", "DZYZ726823604yqwJLS");  //印章编码

            //data_fields
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("CDSYKSRQ", "2020-07-11");
            jsonObject1.put("CQF", "广州市番禺区石楼镇海心村股份合作经济社");
            jsonObject1.put("CYRMC", "91440101MA5CJMYA97");
            jsonObject1.put("CYRSFZJLX", "70");
            jsonObject1.put("CZF", "广州市番禺区石楼镇海心村股份合作经济社");
            jsonObject1.put("FWDZ", "广州市番禺区石楼镇海心村23队第七涧（土名）");
            jsonObject1.put("FZJGMC", "番禺区石楼镇海心村民委员会");
            jsonObject1.put("FZJGSSXZQHDM", "440113012001");
            jsonObject1.put("FZJGZZJGDM", "700001354");
            jsonObject1.put("FZRQ", "2020-07-11");
            jsonObject1.put("JYMJ", "");
            jsonObject1.put("JZMJ", "300");
            jsonObject1.put("XMMC", "研究和试验发展");
            jsonObject1.put("YXQJSRQ", "2021-07-10");
            jsonObject1.put("ZDMJ", "");
            jsonObject1.put("ZZHM", "py146103001");
            jsonObject1.put("ZZMC", "住所（经营场所）场地使用证明");
            parames.put("data_fields", jsonObject1);

            // operator
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("account", "sfq");
            jsonObject2.put("division", "海心村");
            jsonObject2.put("name", "石凤群");
            jsonObject2.put("role", "场地证明用户");
            jsonObject2.put("service_org", "海心村");
            parames.put("operator", jsonObject2);

            String resultString = HttpClient.doPostJson(url, parames.toString());
//            System.out.println("----------制证--resultString--------------------" + resultString);
            JSONObject responseText = JSONObject.fromObject(resultString);
            if ("SUCCESS".equals(responseText.get("ack_code")) && responseText!=null){
                //证照code
                msg = responseText.getString("data");
            } else if ("FAILURE".equals(responseText.get("ack_code")) && responseText!=null){
                // 返回错误
                msg = responseText.getString("errors");

            }
        } catch (Exception e){
            //
        }

        return msg;
       /* {
            "ack_code": "SUCCESS",
                "errors": [],
            "sign": null,
            "sign_method": null,
            "timestamp": null,
            "correlation_id": "2d575eda-e643-49a0-a56a-168775becc02",
            "response_id": "5a935935-1a71-4112-939f-7e0cdb1d08fa",
            "data": {
                     "license_code": "4401002020000011QW",
                     "auth_code": "20201223161845547AA0112031"
                    }
        }*/
    }

    /**
     *  4.7.7获取证照访问令牌
     *      【根 据 电 子 证 照 用 证 码 （ auth_code ） 获 取 电 子 证 照 访 问 令 牌 （license_access_token）】
     */
    public String getLicenseAccessToken(String accessToken, String authCode){
        String license_token = null;
        try {
            String url = "http://10.194.212.27:8081/license-app/v1/license/token?access_token=" + accessToken + "&auth_code=";
            String responseString = HttpClient.sendGet(url, authCode);
            JSONObject jsonObject = JSONObject.fromObject(responseString);
            if ("SUCCESS".equals(jsonObject.get("ack_code")) && jsonObject!=null){

                license_token = JSONObject.fromObject(jsonObject.get("data")).getString("license_access_token");
            }
        } catch (Exception e){
            //
        }
        return license_token;
    }

    /**
     *  4.7.9归档电子文件
     *      【根据电子证照用证码获取用于归档的电子证照文件（PDF 格式）。】
     */
    public String getLicenseDocuments(String accessToken, String authCode){
        String file_path = null;   //返回证照附件，文件路径
        try {
            String url = "http://10.194.212.27:8081/license-app/v1/license/archive?auth_code=" + authCode + "&access_token=";
            String responseString = HttpClient.sendGet(url, accessToken);
            JSONObject jsonObject = JSONObject.fromObject(responseString);
            if ("SUCCESS".equals(jsonObject.get("ack_code")) && jsonObject!=null){
                JSONObject dataObject = JSONObject.fromObject(jsonObject.get("data"));
                String fileName = dataObject.getString("file_name");
                file_path = "d://lic//" + fileName + ".pdf";

                //将base64字符解码保存文件
                Base64Util.decoderBase64File(
                        dataObject.getString("file_data"),
                        file_path,
                        "1231"
                );
            }
        } catch (Exception e){
            //
            System.out.println("----Base64Util-Exception-------"  + e);
        }
        return file_path;
    }

    /**
     * 4.4.4添加附件数据
     *  [添加电子证照附件数据。]
     */
    public void addAttachment(String accessToken, String itemCode){
        try {
            String url = "http://10.194.212.27:8081/license-app/v1/license/200023401726823604440113/create_attachment?access_token=00b82538-1ed8-4755-b92c-832057de7777";
            JSONObject params = new JSONObject();
            params.put("timestamp","2013-06-27 14:51:45");
            params.put("request_id","6c0213f5-2f69-47bd-bd46-139cfd5355a9");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("license_code","4401002020000011HM");
            jsonObject.put("name","新办住所（经营场所）场地使用证明");
            jsonObject.put("file_type",".pdf");
            jsonObject.put("file_data", encodeBase64File("d://20201217112852.jpg")); //附件数据（base64 转码）
            jsonObject.put("is_license_image", "false");
            jsonObject.put("is_show_template", "true");
            jsonObject.put("description", "1");
            params.put("data", jsonObject);
//            System.out.println("------------------添加附件数据-------------------" + HttpClient.doPostJson(url, params.toString()));
//        {"ack_code":"SUCCESS","errors":[],"sign":null,"sign_method":null,"timestamp":null,"correlation_id":null,"response_id":"fd777960-7deb-49fd-9003-0765ecbedcec","data":{"attachment_code":"99f8da52-b839-4561-8de1-fc24c3abd16b"}}

        } catch (Exception e){
            //
            e.printStackTrace();
        }
    }

    /**
     * 4.5.1签发一张证照
     *  [正式签发一张电子证照]
     */
    public void  issueAttachment(){

    }
    /**
     *  将文件转成base64 字符串
     * @param path
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * 4.4.7下载单个附件
     *  【根据附件唯一标识获取单个附件数据】
     */
    public void downloadAttachment (String accessToken, String itemCode, String attachmentCode){
        String url = "http://10.194.212.27:8081/license-app/v1/license/" + itemCode + "/get_attachment?access_token=" + accessToken;
        Map<String, String> map = new HashMap<>();
        map.put("access_token", "");
        map.put("item_code", "");
        map.put("attachment_code", "");
        HttpClient.doGet(url, map);

    }

    /**
     *  4.7.1 查询本人证照
     *      【列举一个持证人所有的电子证照清单，同时对结果清单每一项生成用证码。】
     * @param accessToken     访问令牌
     * @param credential     实名登录凭证token
     * @return     持证人所有的电子证照清单
     */
    public String getLicenceList (String accessToken , String credential){
        String url = "http://10.194.212.27:8081/license-app/v1/license/webpage?access_token=" + accessToken;
        JSONObject params = new JSONObject();
        params.put("credential","11");
        String responseText = HttpClient.doPostJson(url, params.toString());
        return responseText;
    }


    /**
     *  4.7.2 网上申办用证
     *      【列举一个持证人所有的电子证照清单，同时对结果清单每一项生成用证码】
     * @param accessToken     访问令牌
     * @param credential     实名登录凭证token
     * @return     持证人所有的电子证照清单
     */
    public String getCertificateHolderList (String accessToken , String credential){
        String url = "http://10.194.212.27:8081/license-app/v1/license/holder?access_token=" + accessToken;
        JSONObject params = new JSONObject();
        params.put("service_item_code", "");    //事项编码
        params.put("service_item_name", "");   //事项名称
        params.put("credential","11");      //实名登录凭证token
        String responseText = HttpClient.doPostJson(url, params.toString());
        return responseText;
    }

    /**
     *  4.7.7 提取证照数据
     *      【根据用证码提取一个电子证照的信息。】
     * @param accessToken     访问令牌
     * @param credential     实名登录凭证token
     * @return     持证人所有的电子证照清单
     */
    public String s(String accessToken){
        String url = "http://10.194.212.27:8081/license-app/v1/license/get_license";
        Map<String, String> map = new HashMap<>();
        map.put("access_token", "");
        map.put("auth_code", "");
        String responseText = HttpClient.doGet(url, map);
        return responseText;
    }
}
