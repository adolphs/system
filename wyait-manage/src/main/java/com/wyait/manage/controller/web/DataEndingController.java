package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.DataEnding;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.db1.DataEndingService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/13 09:16
 */
@Controller
@RequestMapping("/dataEnding")
public class DataEndingController {

    private static final Logger logger = LoggerFactory
            .getLogger(DataEndingController.class);

    @Autowired
    private DataEndingService dataEndingService;

    /**
     * 进入材料管理页面
     * @return
     */
    @RequestMapping("/dataEndingList")
    public String dataEndingList(){
        return "data/dataEndingList";
    }

    /**
     * 根据ID进入详情页
     * @param dataId
     * @return
     */
    @RequestMapping("/dataEndingDetails")
    public ModelAndView dataEndingDetails(Integer dataId){
        ModelAndView view = new ModelAndView();
        view.setViewName("data/dataEndingDetails");
        return view;
    }

    /**
     * 分页查询材料支持筛选加分页
     * @param page
     * @param limit
     * @param dataEnding
     * @return
     */
    @RequestMapping("/getDataList")
    @ResponseBody
    public PageDataResult getDataList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, DataEnding dataEnding){
        logger.debug("分页查询材料列表！搜索条件：DataEnding：" + dataEnding + ",page:" + page
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
            pdr = dataEndingService.getData(dataEnding, page, limit);
            logger.debug("事项列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }

        return pdr;
    }

    /**
     * 新增或者修改
     * @param dataEnding
     * @return
     */
    @PostMapping("/addDataEnding")
    @ResponseBody
    public String addDataEnding(DataEnding dataEnding){
        logger.debug("设置材料[新增或更新]！dataEnding:" + dataEnding );
        try {
            if (null == dataEnding) {
                logger.debug("材料[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();

            if (null == existUser) {
                logger.debug("置材料[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
//            logSystemService.addLog(existUser,dooo);
            // 设置用户[新增或更新]
            logger.info("设置材料[新增或更新]成功！data=" + dataEnding + "，操作的用户ID=" + existUser.getId());
            return dataEndingService.addDataEnding(dataEnding);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 查询所有的审批结果
     * @return
     */
    @ResponseBody
    @RequestMapping("/setAll")
    public List<DataEnding> setAll(){
        return dataEndingService.setAll();
    }

    /**
     * 添加审批结果和事项的关系
     * @param dataEndId
     * @param doooId
     * @return
     */
    @RequestMapping("/addDataEndingAndDooo")
    @ResponseBody
    public String addDataEndingAndDooo(Integer dataEndId,Integer doooId){
        try {
            return dataEndingService.addDataEndingAndDooo(dataEndId,doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置审批结果关联事项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据事项ID删除对应的关联结果
     * @param dataEndId
     * @param doooId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDataEndingAndDooo")
    public String delDataEndingAndDooo(Integer dataEndId,Integer doooId){
        try {
            return dataEndingService.delDataEndingAndDooo(dataEndId,doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置审批结果关联事项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @ResponseBody
    @RequestMapping("/getDataEndingByDoooId")
    public List<DataEnding> getDataEndingByDoooId(Integer id,Integer type){
        try {
            return dataEndingService.getDataEndingByDoooId(id,type);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置审批结果关联事项异常！", e);
            return null;
        }
    }

    /**
     * 添加审批结果和事项的关系
     * @param dataEndId
     * @param comboId
     * @return
     */
    @RequestMapping("/addDataEndingAndCombo")
    @ResponseBody
    public String addDataEndingAndCombo(Integer dataEndId,Integer comboId){
        try {
            return dataEndingService.addDataEndingAndCombo(dataEndId,comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置审批结果关联事项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @ResponseBody
    @RequestMapping("/delDataEndingAndCombo")
    public String delDataEndingAndCombo(Integer dataEndId,Integer comboId){
        try {
            return dataEndingService.delDataEndingAndCombo(dataEndId,comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置审批结果关联事项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }
}
