package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.ProgramWindow;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.db1.DepartmentService;
import com.wyait.manage.service.db1.DoooService;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:12
 */
@Controller
@RequestMapping("/dooo")
public class DoooController {

    private static final Logger logger = LoggerFactory
            .getLogger(DoooController.class);

    @Autowired
    private DoooService doooService;
    @Autowired
    private LogSystemService logSystemService;
    @Autowired
    private DepartmentService departmentService;
    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/doooList")
    public String doooList(){
        return "dooo/doooList";
    }


    @RequestMapping("/approvalDoooList")
    public String approvalDoooList(){
        return "dooo/approvalDoooList";
    }

    @RequestMapping("/doooDetails")
    public ModelAndView doooDetails(Integer id){
        ModelAndView view = new ModelAndView();
        view.setViewName("dooo/doooDetails");
        if (id != null){
            //查询事项
            Dooo dooo = doooService.selectById(id);
            String departmentName = null;
            if (dooo.getDepartmentId() != null){
                departmentName = departmentService.selBydepartmentId(dooo.getDepartmentId());

            }
            view.addObject("departmentName",departmentName);
            view.addObject("dooo",dooo);
            view.addObject("doooId",id);
        }
        return view;
    }
    /**
     * 分页查询事项支持筛选加分页
     * @param page
     * @param limit
     * @param dooo
     * @return
     */
    @RequestMapping("/getDoooList")
    @ResponseBody
    public PageDataResult getDoooList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, Dooo dooo){
        logger.debug("分页查询事项列表！搜索条件：dooo：" + dooo + ",page:" + page
                + ",每页记录数量limit:" + limit);
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取事项管理列表
            pdr = doooService.getDooos(dooo, page, limit);
            logger.debug("事项列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }

        return pdr;
    }

    @PostMapping("/getDooo")
    @ResponseBody
    public String getDooo(Dooo dooo){
        logger.debug("设置事项[新增或更新]！dooo:" + dooo );
        try {
            if (null == dooo) {
                logger.debug("事项[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();

            if (null == existUser) {
                logger.debug("置事项[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            logSystemService.addLog(existUser,dooo);
            // 设置用户[新增或更新]
            logger.info("设置事项[新增或更新]成功！dooo=" + dooo + "，操作的用户ID=" + existUser.getId());
            return doooService.setDooo(dooo);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据事项id查找事项
     * @param doooId
     * @return
     */
    @RequestMapping("/getDoooByDoooId")
    @ResponseBody
    public Dooo getDoooByDoooId(Integer doooId){
        logger.info("根据事项ID查询事项："+ doooId);
        return doooService.getDoooById(doooId);
    }

    /**
     * 删除事项
     * @param doooId
     * @return
     */
    @PostMapping("/delDooo")
    @ResponseBody
    public String delDooo(Integer doooId){
        logger.debug("删除事项！doooId:" + doooId );
        try{
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            logSystemService.del(existUser,doooId,2);
            return doooService.delDooo(doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }

    }

    @ResponseBody
    @GetMapping("/getDoooList")
    public List<Dooo> getDoooList(Integer departmentId){
        return doooService.getDoooList(departmentId);
    }

    /**
     * 事项添加通用材料
     * @param dataId  材料ID
     * @param doooId  事项ID
     * @return
     */
    @ResponseBody
    @RequestMapping("/addDoooAndData")
    public String addDoooAndData(Integer dataId,Integer doooId){
        if (dataId == null || doooId == null){
            return "情形选择出现错误，请刷新后重新关联";
        }

        return doooService.addDoooAndData(dataId,doooId);
    }


    /**
     * 通过审批
     * @param doooId
     * @param approvalType
     * @param approvalText
     * @return
     */
    @ResponseBody
    @RequestMapping("/putApprovalType")
    public String putApprovalType(Integer doooId,Integer approvalType,String approvalText){
        try{
            return doooService.putApprovalType(doooId,approvalType,approvalText);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据事项ID查询窗口
     * @param doooId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProgramWindowList")
    public List<ProgramWindow> getProgramWindowList(String doooId){
        try{
            return doooService.getProgramWindowList(doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[删除]异常！", e);
            return null;
        }
    }

    /**
     * 新增窗口
     * @param programWindow
     * @return
     */
    @PostMapping("/operatingProgramWindow")
    @ResponseBody
    public String operatingProgramWindow(ProgramWindow programWindow){
        logger.debug("窗口[新增或更新]！ProgramWindow:" + programWindow );
        try {
            if (null == programWindow) {
                logger.debug("窗口[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();

            if (null == existUser) {
                logger.debug("置事项[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            // 设置用户[新增或更新]
            logger.info("窗口[新增或更新]成功！programWindow=" + programWindow + "，操作的用户ID=" + existUser.getId());
            return doooService.operatingProgramWindow(programWindow);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

}
