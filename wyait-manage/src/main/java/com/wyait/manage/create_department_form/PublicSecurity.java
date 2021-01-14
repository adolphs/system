/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PublicSecurity
 * Author:   Administrator
 * Date:     2020/12/29
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
import java.util.*;

/**
 * 〈公安〉
 *
 * @author Administrator
 * @create 2020/12/29 11:25
 * @since 1.0.0
 */
public class PublicSecurity {

    public static void departmentForm(String s, String s1) {
    }


    /**
     * 公路路政许可交警部门意见表
     * @param data
     * @return
     */
    private static void  highway(JSONObject data){
        //图片路径，请注意你是linux还是windows

        /**
         * streamCopyFile("D:\\DEV\\gj.txt", "c:\\a1\\b2\\c3\\", "121.text");
         */
        System.out.println("------1.复制模板-------");
        Calendar now = Calendar.getInstance();
        String wordPath="e:\\word_file\\gongan\\";
        String modelName="公路路政许可交警部门意见表.docx";
        String outputName="USER_公路路政许可交警部门意见表.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                //申请日期
                put("year", now.get(Calendar.YEAR));   //当前年
                put("month", (now.get(Calendar.MONTH) + 1));//当前月
                put("day", now.get(Calendar.DAY_OF_MONTH));//当前日
                Long time = new Date().getTime();
                System.out.println("--year----" + now.get(Calendar.YEAR));   //当前年
                System.out.println("--month----" + (now.get(Calendar.MONTH) + 1));//当前月
                System.out.println("--day----" + now.get(Calendar.DAY_OF_MONTH));//当前日
                System.out.println("--time----" + time);


                //本地图片
                put("personLiable","鞠婧祎☑☑");   // 建设单位-负责人
                put("phone1","女");          //建设单位-联系电话
                put("personLiable2","前SNH48");  //施工单位-负责人
                put("phone2","女");          //施工单位-联系电话
                put("projectName","19940618");   //工程项目名称
                put("roadSection","鞠婧祎，综艺节目《快乐大本营》。");   //施工路段
                //占道情况
                put("laneArea","19940618");   //车行道面积
                put("long1","19940618");   //车道-长
                put("wide1","19940618");   //车道-宽
                put("sidewalkArea","19940618");   //人行道面积
                put("long2","19940618");   //人行道-长
                put("wide2","19940618");   //人行道-宽
                //s施工类别
                put("classification", "☑" + "其他");
                //列表
               /* put("active",new NumbericRenderData(new ArrayList<TextRenderData>(){{
                    add(new TextRenderData("FF00FF", "2013年 以《剧场女神》公演正式出道"));
                    add(new TextRenderData("FF00FF", "2014年 拍摄个人首支MV《足球派对》"));
                    add(new TextRenderData("FF00FF", "2015年 发行出道两周年EP《青春的约定》"));
                    add(new TextRenderData("FF00FF", "2016年 主演玄幻剧《九州天空城》"));
                }}));*/

                //表格，2行2列
                /*put("tables", new TableRenderData(new ArrayList<RenderData>(){{
                    add(new TextRenderData("d0d0d0", "节目"));
                    add(new TextRenderData("d0d0d0", "次数"));
                }},new ArrayList<Object>(){{
                    add("《SNH星剧院公演》;999");
                    add("《敢ZUO敢为女声秀》;4");
                    add("《快乐大本营》;2 ☑");
                }}, "no datas", 10600));*/
//				//网路图片
//                put("picture", new PictureRenderData(200, 250, ".png", BytePictureUtils.getUrlByteArray("https://pic.baike.soso.com/ugc/baikepic2/18293/cut-20170602162513-2088410512.jpg/300")));

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
        System.out.println("-------------------往模板添加数据-------------------");
    }

    /**
     * 占用、挖掘城市道路申请表
     * @param data
     * @return
     */
    private static void  urbanRd(JSONObject data, String modelName, String outputName){
        //图片路径，请注意你是linux还是windows
        String wordPath="e:\\word_file\\gongan\\";
//        String modelName="占用、挖掘城市道路申请表.docx";
//        String outputName="user_占用、挖掘城市道路申请表.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                //申请日期
                put("year", "2020");
                put("month", "12");
                put("day", "30");
                put("address","鞠婧祎☑☑");   //建设单位--地址
                put("phone","前SNH48");     //建设单位--电话
                put("representative","女");   // 法定代表人
                put("contacts","19940618");     // 联系人
                put("address2","鞠婧祎☑☑");   //施工单位--地址
                put("phone2","前SNH48");     //施工单位--电话
                put("personLiable", "1");   //安全负责人
                put("plContacts", "2");   //安全负责 ---联系人

                //城市规划部门批准文号
                //占用、挖掘路名和起止路段
                //占用道路情况
                //挖掘道路情况
                put("long1", "3"); //沥青路--长
                put("wide1", "4"); //沥青路--宽

                //时间安排
                //挖掘类别
                put("remark","鞠婧祎，1994年6月18日出生于四川遂宁，毕业于四川音乐学院附属中学，中国女演员、歌手，原SNH48 TEAM NII成员[1]。2013年9月5日，升格为SNH48 TEAM NII二期生正式成员；11月2日，以《剧场女神》公演正式出道。2014年6月7日，拍摄个人首支MV《足球派对》；7月26日，参加SNH48“一心向前”演唱会暨SNH48第一届总选举，演唱《流着泪微笑》并获SNH48总选举第四名。2015年1月15日，发行出道两周年EP《青春的约定》。2016年12月10日，获“星光大赏”年度新锐电视剧女演员。2017年1月1日，参加安徽卫视《国剧盛典》；3月27日，获第24届东方风云榜音乐盛典“年度飞跃歌手”奖[2]；5月4日，获团中央“五四优秀青年”称号；5月25日，发行第二张个人EP《等不到你》；[3]7月29日，参加“我心翱翔”第四届总选举发布演唱会，获得SNH48第四届总选举第1名；[4]12月15日，SNH48官方宣布，鞠婧祎正式从SNH48 Group单飞、成立个人工作室。2018年3月26日，获第25届《东方风云榜》音乐盛典年度跨界艺人奖。7月28日，参加湖南卫视综艺节目《快乐大本营》。");
                put("active",new NumbericRenderData(new ArrayList<TextRenderData>(){{
                    add(new TextRenderData("FF00FF", "2013年 以《剧场女神》公演正式出道"));
                    add(new TextRenderData("FF00FF", "2014年 拍摄个人首支MV《足球派对》"));
                    add(new TextRenderData("FF00FF", "2015年 发行出道两周年EP《青春的约定》"));
                    add(new TextRenderData("FF00FF", "2016年 主演玄幻剧《九州天空城》"));
                }}));
                put("tables", new TableRenderData(new ArrayList<RenderData>(){{
                    add(new TextRenderData("d0d0d0", "节目"));
                    add(new TextRenderData("d0d0d0", "次数"));
                }}, new ArrayList<Object>(){{
                    add("《SNH星剧院公演》;999");
                    add("《敢ZUO敢为女声秀》;4");
                    add("《快乐大本营》;2 ☑");
                }}, "no datas", 10600));
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
