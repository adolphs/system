/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TrafficDepartment
 * Author:   Administrator
 * Date:     2020/12/30
 * History:
 */
package com.wyait.manage.create_department_form;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.util.BytePictureUtils;
import net.sf.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈交通部门〉
 *
 * @author Administrator
 * @create 2020/12/30 9:52
 * @since 1.0.0
 */
public class TrafficDepartment {

    public static void departmentForm(String s, String s1) {
    }


    /**
     * 路政许可申请
     */
    private static void  roadPermit(JSONObject data){
        //图片路径，请注意你是linux还是windows
//        String wordPath="C:\\Users\\Administrator\\Desktop\\";
        String wordPath="e:\\word_file\\jiaotong\\";
        String modelName="路政许可申请表.docx";
        String outputName="user_路政许可申请表.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("data", "2020年12月12日");   //申请日期
                put("reason", "申请理由");    //许可申请事由
                /** 申请人基本情况  */
                put("applicant","鞠婧祎☑☑"); //申请人
                put("representative","女");   // 法定代表人
                put("name","前SNH48");   //证件名称
                put("number","女");    //证件号码
                put("address","鞠婧祎☑☑");   //地址
                put("postalCode","19940618");  //邮政编码
                put("telephone", ""); // 固定电话
                put("mobilePhone", ""); //手机
                put("mailbox", "");     //电子邮箱
                put("agent", "");   //代理人
                put("identity", "");    //身份
                put("agentNumber", "");   //代理人--身份证号码
                put("agentTelephone", "");   //代理人-- 固定电话
                put("agentPhone", "");   //代理人 -- 手机
                put("agentMailbox", "");   //代理人 --电子邮箱

                /** 申请内容*/
                put("content","鞠婧祎，1994年6月18日出生于四川遂宁，毕业于四川音乐学院附属中学，中国女演员、歌手，原SNH48 TEAM NII成员[1]。2013年9月5日，升格为SNH48 TEAM NII二期生正式成员；");   //申请事项具体内容
                put("matterPlace", "线K     ＋       至K        ＋         左（    ）/右（    ）侧"); //申请事项地点
                put("term", "xx年xx月xx日");  //申请许可期限
                put("remark","11月2日，以《剧场女神》公演正式出道。2014年6月7日，拍摄个人首支MV《足球派对》；7月26日，参加SNH48“一心向前”演唱会暨SNH48第一届总选举，演唱《流着泪微笑》并获SNH48总选举第四名。2015年1月15日，发行出道两周年EP《青春的约定》。2016年12月10日，获“星光大赏”年度新锐电视剧女演员。2017年1月1日，参加安徽卫视《国剧盛典》；3月27日，获第24届东方风云榜音乐盛典“年度飞跃歌手”奖[2]；5月4日，获团中央“五四优秀青年”称号；5月25日，发行第二张个人EP《等不到你》；[3]7月29日，参加“我心翱翔”第四届总选举发布演唱会，获得SNH48第四届总选举第1名；[4]12月15日，SNH48官方宣布，鞠婧祎正式从SNH48 Group单飞、成立个人工作室。2018年3月26日，获第25届《东方风云榜》音乐盛典年度跨界艺人奖。7月28日，参加湖南卫视综艺节目《快乐大本营》。");
                put("active",new NumbericRenderData(new ArrayList<TextRenderData>(){{
                    add(new TextRenderData("FF00FF", "2013年 以《剧场女神》公演正式出道"));
                    add(new TextRenderData("FF00FF", "2014年 拍摄个人首支MV《足球派对》"));
                    add(new TextRenderData("FF00FF", "2015年 发行出道两周年EP《青春的约定》"));
                    add(new TextRenderData("FF00FF", "2016年 主演玄幻剧《九州天空城》"));
                }}));
//				//网路图片
                put("picture", new PictureRenderData(200, 250, ".png", BytePictureUtils.getUrlByteArray("https://pic.baike.soso.com/ugc/baikepic2/18293/cut-20170602162513-2088410512.jpg/300")));

            }
        };

        XWPFTemplate template = XWPFTemplate.compile(wordPath+modelName)
                .render(datas);
        FileOutputStream out;
        try {
            out = new FileOutputStream(wordPath+outputName);
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