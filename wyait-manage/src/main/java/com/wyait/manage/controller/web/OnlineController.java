package com.wyait.manage.controller.web;

import com.wyait.manage.utils.PageDataResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/online")
public class OnlineController {

    /**
     * 进入进度查询页面
     * @return
     */
    @RequestMapping("/progressQuery")
    public String progressQuery(){
        return "online/progressQuery";
    }

    @RequestMapping("/progressList")
    @ResponseBody
    public PageDataResult progressList(){
        PageDataResult pdr = new PageDataResult();
        pdr.setCode(200);
        pdr.setTotals(10);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name","康旭尧");
        map1.put("ID","340202197104106891");
        map1.put("type","套餐");
        map1.put("business","开办餐厅");
        map1.put("newTime","2020-11-05 17:58:01");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("name","冯伟祺");
        map2.put("ID","430900198306141777");
        map2.put("type","套餐");
        map2.put("business","排水许可");
        map2.put("newTime","2020-11-05 17:58:01");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>();
        map3.put("name","吕雅畅");
        map3.put("ID","141130198509277099");
        map3.put("type","套餐");
        map3.put("business","开办便利店");
        map3.put("newTime","2020-11-05 17:58:01");
        list.add(map3);
        Map<String, String> map4 = new HashMap<>();
        map4.put("name","吕雅畅");
        map4.put("ID","141130198509277099");
        map4.put("type","事项");
        map4.put("business","个体工商户注册登记");
        map4.put("newTime","2020-11-05 17:58:01");
        list.add(map4);
        pdr.setList(list);
        return pdr;
    }

    /**
     * 进入查询详情页
     * @return
     */
    @RequestMapping("/queryDetails")
    public String queryDetails(){
        return "online/queryDetails";
    }

    /**
     * 进入表单设置页面
     * @return
     */
    @RequestMapping("/formSetUp")
    public String formSetUp(){
        return "online/formSetUp";
    }

    @RequestMapping("/formSetUpDetails")
    public String formSetUpDetails(){
        return "online/formSetUpDetails";
    }

    @ResponseBody
    @RequestMapping("/getFormList")
    public PageDataResult getFormList(){
        PageDataResult pdr = new PageDataResult();
        pdr.setCode(200);
        pdr.setTotals(10);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name","开办便利单综合表单模板");
        map1.put("type","套餐");
        map1.put("time","2020-11-05 17:58:01");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("name","开办餐厅综合表单模板");
        map2.put("type","套餐");
        map2.put("time","2020-11-05 17:58:01");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>();
        map3.put("name","个体工商户注册登记");
        map3.put("type","事项");
        map3.put("time","2020-11-05 17:58:01");
        list.add(map3);
        Map<String, String> map4 = new HashMap<>();
        map4.put("name","我要用电（社会投资-简易低风险工程）");
        map4.put("type","套餐");
        map4.put("time","2020-11-05 17:58:01");
        list.add(map4);
        Map<String, String> map5 = new HashMap<>();
        map5.put("name","事业单位法人变更登记");
        map5.put("type","事项");
        map5.put("time","2020-11-05 17:58:01");
        list.add(map5);

        pdr.setList(list);
        return pdr;
    }
}
