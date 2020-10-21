package com.wyait.manage.controller.api;

import com.wyait.manage.utils.HttpClientUtil;
import com.wyait.manage.utils.HttpResult;
import com.wyait.manage.utils.HttpService;
import com.wyait.manage.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api/callback/authentication")
@RestController
public class CallbackController {

    @Autowired
    HttpService httpService;

    /**
     * 获取授权码 grant_code
     * @throws IOException
     */
    @RequestMapping("/getGarntCode")
    public String getGarntCode() throws IOException {
        String url = "https://tyrztest.gd.gov.cn/tif/sso/connect/page/oauth2/access_token?scope=all&client_id=gzrz_gzkpqyfw&redirect_uri=https%3a%2f%2fqy.gdfda.gov.cn%2fesso%2flogin%3fclient_name%3dGDFDASSO&grant_type=code&client_secret=GeOPBnH4fn5o&service=initService&code=1111";
        String response = HttpClientUtil.httpsRequest(url, "GET", "");
        System.out.println(JsonUtil.jsonToMap(response).get("grant_code"));
        return response;
    }

    /**
     * 获取授权码  access_token
     * @throws IOException
     */
    @RequestMapping("/getAccessToken")
    public String identityAuthentication() throws IOException {
        String url = "https://tyrztest.gd.gov.cn/tif/sso/connect/page/oauth2/access_token?" +
                "scope=all" +
                "&client_id=10054" +
                "&redirect_uri=https%3a%2f%2fqy.gdfda.gov.cn%2fesso%2flogin%3fclient_name%3dGDFDASSO" +
                "&grant_type=code" +
                "&client_secret=GeOPBnH4fn5o" +
                "&service=initService" +
                "&code=1111";
        String response = HttpClientUtil.httpsRequest(url, "GET", "");
        System.out.println(response);
        return response;
    }

    /**
     * 获取用户信息
     * @throws IOException
     */
    @RequestMapping("/getPerson")
    public String getPerson() throws IOException {
        String url = "https://tyrztest.gd.gov.cn/tif/sso/connect/page/oauth2/access_token?scope=all&client_id=gzrz_gzkpqyfw&redirect_uri=https%3a%2f%2fqy.gdfda.gov.cn%2fesso%2flogin%3fclient_name%3dGDFDASSO&grant_type=code&client_secret=GeOPBnH4fn5o&service=initService&code=1111";
        String response = HttpClientUtil.httpsRequest(url, "GET", "");
        System.out.println(response);
        return response;
    }
}
