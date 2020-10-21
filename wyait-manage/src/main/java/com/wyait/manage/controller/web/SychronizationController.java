package com.wyait.manage.controller.web;

import com.wyait.manage.service.db1.ProgramDefService;
import com.wyait.manage.dao.ProgramDefDao;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db2.Db2ProgramDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
