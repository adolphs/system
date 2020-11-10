package com.wyait.manage.controller.web;

import com.wyait.manage.service.db1.ProgramDefService;
import com.wyait.manage.dao.ProgramDefDao;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db2.Db2ProgramDefService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省事项数据库同源
 */
@Controller
@RequestMapping("/sychronization")
public class SychronizationController {

    private static final int content = 50;   //每一页查询20条

    @Autowired
    private Db2ProgramDefService programDefService2;

    @Autowired
    private ProgramDefService programService1;
    @Autowired
    private ProgramDefDao prograDao;

    /**
     *  省事项库数据列表
     * @param programName  事项名称
     * @param pageNum    当前页
     * @param model
     * @return   省事项库数据集
     */
    @RequestMapping("/dataSychronization")
//    @ResponseBody  @Transactional(transactionManager="transactionManager1")
    public String dataSychronization(String programName, Integer pageNum, ModelMap model){
        model.addAttribute("programName", programName);
        model.addAttribute("pageNum" , pageNum ==null?1:pageNum);
        int totalPageNum = programDefService2.getMatterCounts(programName);//(totalRecord +pageSize - 1) / pageSize;
        int end = 50;
        if (pageNum!=null && pageNum>1){
            /*content = (pageNum-1) * content ;   //（当前页-1）*（每页数量）=起始值
            totalPageNum = (totalPageNum+pageNum-1) / pageNum;*/
            end = content * pageNum;
            pageNum = (pageNum-1)*content;
        } else {
            pageNum = 0;
        }
        List<ProgramDef> programDefList = programDefService2.fetchAllmatterList(programName,pageNum, end);
        model.addAttribute("programDefList", programDefList);
        model.addAttribute("totalPages", totalPageNum);   //总页数
        return "sychronization/dataSychronization";
    }

    /**
     * 同步数据
     * @param programId   事项ID
     */
    @RequestMapping("/dasyci")
    @ResponseBody
    public String getDataSys(String programId){
        String resultMsg = null;
        ProgramDef programDefs = null;
        if (!"".equals(programId) && programId!=null ){
            programDefs = programDefService2.fetchByIdBasicsData(programId);  //事项材料

            /** 材料表 */
            List<ProgramMaterial> materialist = programDefService2.fetchByIdMatter(programId);

            /** 办事窗口 */
            ProgramWindow programWindow = programDefService2.fetchByIdMaterialWindow (programId);

            /** 办理结果 */
            ProgramLicenseResult licenseResu = programDefService2.fetchByIdMaterResult (programId);

            /** 审批咨询，投诉  */
            List<ProgramItemAsk> itemAsk = programDefService2.fetchByIdMaterItemAsk (programId);

            /** 权力与义务 */
            List<ProgramItemRightDuty> itemRightDutyList = programDefService2.fetchByIDRightDuty(programId);

            resultMsg = programService1.sysMatterIntf(programDefs, materialist, programWindow, licenseResu, itemAsk, itemRightDutyList);
        }
        return resultMsg;
    }

    /**
     * 套餐同步
     * @return
     */
    @RequestMapping("/doooSychronization")
    @ResponseBody
    public List<ProgramDef> doooSychronization(String programName, Integer start, Integer end, ModelMap model){

        return  programDefService2.fetchAllmatterList(programName,1, 20);
    }

    @RequestMapping("/doooDemo")
    public String doooDemo(){
        return "sychronization/dataDemo";
    }

    @RequestMapping("/dadadadad")
    @ResponseBody
    public PageDataResult dadadadad(String name){
        PageDataResult pdr = new PageDataResult();
        List<Map<String, String>> list = new ArrayList<>();
        if (name != null){

        }else{
            Map<String, String> map1 = new HashMap<>();
            map1.put("username","个体工商户注册登记");
            map1.put("sex","11440113MB2C92966P4000131004000");
            map1.put("city","1、有经营能力的中国大陆公民、台港澳居民，符合《个体工商户条例》、《个体工商户登记管理办法》、《内地与香港关于建立更紧密经贸关系的安排》、《内地与澳门关于建立更紧密经贸关系的安排》、《广东省工商行政管理局关于台湾居民申办个体工商户登记管理试行办法》、《关于促进两岸农业合作、惠及台湾农民的若干政策措施》、《台湾农民在海峡两岸农业合作试验区和台湾农民创业园申办个体工商户登记管理工作的若干意见》、《广东省商事登记条例》等相关规定的均可以申请办理个体工商户。 2、经营范围不属于法律、行政法规禁止进入的行业。");
            map1.put("sign","线上、线下");
            map1.put("experience","30天");
            map1.put("score","空表下载");
            map1.put("classify","示例样本");
            map1.put("wealth","营业执照副本、营业执照正本、核准开业登记通知书");
            map1.put("sda","操作");
            list.add(map1);
            Map<String, String> map2 = new HashMap<>();
            map2.put("username","个人独资企业设立登记");
            map2.put("sex","11440113MB2C92966P4000131004000");
            map2.put("city","1.国家机构以外的社会组织或者个人（大陆境内）利用非国家财政性经费，在各行政区域内面向社会举办中外合作开办教育机构，由各区教育行政部门按照国家规定的权限审批。\n" +
                    "2.申请举办中外合作学前教育机构的个人，应当具有政治权利和完全民事行为能力，无违规办学的经历。申请举办中外合作开办学前教育机构的社会组织应当具有法人资格，企事业经营状况良好，且无违规办学的经历。 3.设立民办学校应当符合当地教育发展的需求，具备《中华人民共和国教育法》、《中华人民共和国民办教育促进法》及其他法律法规规定的条件。");
            map2.put("sign","线上、线下");
            map2.put("experience","20天");
            map2.put("score","空表下载");
            map2.put("classify","示例样本");
            map2.put("wealth","营业执照副本、营业执照正本、核准开业登记通知书");
            map2.put("sda","操作");
            list.add(map2);
            Map<String, String> map3 = new HashMap<>();
            map3.put("username","食品经营许可证核发（除实施申请人承诺制的小餐饮之外的食品经营者）");
            map3.put("sex","11440113MB2C92966P4000131024000");
            map3.put("city","符合以下全部条件的单位可以提出申请：\n" +
                    "1.具有与经营的食品品种、数量相适应的食品原料处理和食品加工、销售、贮存等场所，保持该场所环境整洁，并与有毒、有害场所以及其他污染源保持规定的距离；\n" +
                    "2.具有与经营的食品品种、数量相适应的经营设备或者设施，有相应的消毒、更衣、盥洗、采光、照明、通风、防腐、防尘、防蝇、防鼠、防虫、洗涤以及处理废水、存放垃圾和废弃物的设备或者设施；\n" +
                    "3.有专职或者兼职的食品安全管理人员和保证食品安全的规章制度；\n" +
                    "4.具有合理的设备布局和工艺流程，防止待加工食品与直接入口食品、原料与成品交叉污染，避免食品接触有毒物、不洁物；\n" +
                    "5.取得《企业法人登记证》或《营业执照》等主体资格证明文件。申办单位食堂许可，应取得法人登记证、社团登记证或营业执照等主体资格证明文件。 6.法律、法规规定的其他条件。");
            map3.put("sign","线下");
            map3.put("experience","10天");
            map3.put("score","空表下载");
            map3.put("classify","示例样本");
            map3.put("wealth","食品经营许可证");
            map3.put("sda","操作");
            list.add(map3);
            Map<String, String> map4 = new HashMap<>();
            map4.put("username","中外合作开办学前教育机构审批");
            map4.put("sex","1144011300752361314440102001001");
            map4.put("city","1.国家机构以外的社会组织或者个人（大陆境内）利用非国家财政性经费，在各行政区域内面向社会举办中外合作开办教育机构，由各区教育行政部门按照国家规定的权限审批。\n" +
                    "2.申请举办中外合作学前教育机构的个人，应当具有政治权利和完全民事行为能力，无违规办学的经历。申请举办中外合作开办学前教育机构的社会组织应当具有法人资格，企事业经营状况良好，且无违规办学的经历。 3.设立民办学校应当符合当地教育发展的需求，具备《中华人民共和国教育法》、《中华人民共和国民办教育促进法》及其他法律法规规定的条件。");
            map4.put("sign","线上、线下");
            map4.put("experience","15天");
            map4.put("score","空表下载");
            map4.put("classify","示例样本");
            map4.put("wealth","中华人民共和国中外合作办学许可证、内地与港澳台合作办学许可证");
            map4.put("sda","操作");
            list.add(map4);
        }
        pdr.setList(list);
        pdr.setTotals(10);
        pdr.setCode(200);
        return pdr;
    }
}
