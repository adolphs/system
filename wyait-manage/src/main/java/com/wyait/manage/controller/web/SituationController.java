package com.wyait.manage.controller.web;

import com.wyait.manage.entity.DataAndSiutationVO;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db1.DataService;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.service.db1.SituationService;
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
 * @date 2020/6/28 15:59
 */

@Controller
@RequestMapping("/situation")
public class SituationController {

    private static final Logger logger = LoggerFactory
            .getLogger(SituationController.class);

    @Autowired
    private SituationService situationService;
    @Autowired
    private DataService dataService;
    @Autowired
    private LogSystemService logSystemService;
    /**
     * 进入情形管理页面
     * @return
     */
    @RequestMapping("/situationList")
    public String doooList(){
        return "situation/situationList";
    }

    @RequestMapping("/doooAdd")
    public String doooAdd(){
        return "situation/doooAdd";
    }

    @RequestMapping("/situationDetails")
    public ModelAndView situationDetails(Integer situationId){
        ModelAndView view = new ModelAndView();
        if (situationId != null){
            //查询事项
            Situation situation = situationService.selectById(situationId);
            List<Situation> situations = situationService.selectPidBySituationId(situationId);
            view.addObject("situation",situation);
            view.addObject("situationId",situationId);
            view.addObject("doooId",situation.getDoooId());
//            if (situation.getNodesType() == 0){
//                view.setViewName("situation/situationDetailsEnd");
                //查询所有材料
//                List<DataAndSiutationVO> DataAndSiutationVOs = dataService.getDataAndSituationVOById(situationId);
                //查询所有选项
//                List<SituationDetails> situationDetails = situationService.selectSituationDetailsBySituationId(situationId);
//                view.addObject("situationDetails",situationDetails);
//                view.addObject("DataAndSiutationVOs",DataAndSiutationVOs);
//            }else if(situation.getNodesType() == 1){
//                view.setViewName("situation/situationDetails2");
//                view.addObject("situatios",situations);
//            }else{
                //最后是材料情形了
//                view.setViewName("situation/situationDetailsData");
                view.setViewName("situation/situationDetails2");
                //查询所有材料
                List<DataAndSiutationVO> DataAndSiutationVOs = dataService.getDataAndSituationVOByIdTow(situationId);
                //查询所有选项
                List<SituationDetails> situationDetails = situationService.selectSituationDetailsBySituationId(situationId);
                view.addObject("situationDetails",situationDetails);
                view.addObject("DataAndSiutationVOs",DataAndSiutationVOs);
            }

//        }

        return view;
    }

    /**
     * 分页查询情形支持筛选加分页
     * @param page
     * @param limit
     * @param situation
     * @return
     */
    @RequestMapping("/getSituationList")
    @ResponseBody
    public PageDataResult getSituationList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, Situation situation){
        logger.debug("分页查询情形列表！搜索条件：situation：" + situation + ",page:" + page
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
            pdr = situationService.getSituationList(situation, page, limit);
            logger.debug("事项列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }

        return pdr;
    }

    @PostMapping("/getSituation")
    @ResponseBody
    public String getSituation(Situation situation){
        logger.debug("设置情形[新增或更新]！situation:" + situation );
        try {

            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置情形[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            logSystemService.addLog(existUser,situation);
            // 设置用户[新增或更新]
            logger.info("设置情形[新增或更新]成功！situation=" + situation + "，操作的用户ID=" + existUser.getId());
            return situationService.setSituation(situation);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置情形[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @PostMapping("/getSituationDetails")
    @ResponseBody
    public String getSituationDetails(SituationDetails situationDetails){
        logger.debug("设置情形选项[新增或更新]！situationDetails:" + situationDetails );
        try {
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置情形[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
//            logSystemService.addLog(existUser,situationDetails);
            // 设置用户[新增或更新]
            logger.info("设置情形[新增或更新]成功！situationDetails=" + situationDetails + "，操作的用户ID=" + existUser.getId());
            return situationService.setSituationDetails(situationDetails);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置情形[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @PostMapping("/delSituation")
    @ResponseBody
    public String delSituation(Integer situationId){
        logger.debug("设置情形[删除]！situationDetailsId:" + situationId );
        try {
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
//            logSystemService.del(existUser,situationId,3);
            return  situationService.delSituation(situationId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("设置情形[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @PostMapping("/delSituationDetails")
    @ResponseBody
    public String delSituationDetails(Integer situationDetailsId){
        logger.debug("设置选项[删除]！situationDetailsId:" + situationDetailsId );
        try {
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
//            logSystemService.del(existUser,situationId,3);
            return  situationService.delSituationDetails(situationDetailsId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("设置情形[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据选项添加子情形
     * @param pid
     * @param type
     * @param situationDescribe
     * @param situationId
     * @param situationIdTOW
     * @return
     */
    @ResponseBody
    @RequestMapping("/setSituationTOW")
    public String setSituationTOW(Integer pid,Integer type,String situationDescribe,Integer situationId,Integer situationIdTOW){
        try{
            return situationService.setSituationTOW(pid,type,situationDescribe,situationId,situationIdTOW);
        }catch (Exception e){
            e.printStackTrace();
            return "错误";
        }
    }

    /**
     * 根据选项查看情形
     * @param situationDetailsId
     * @return
     */
    @RequestMapping("/situationDetailsId")
    @ResponseBody
    public List<Situation> findSituationByPid(Integer situationDetailsId){
        try{
            return situationService.findSituationByPid(situationDetailsId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据PId 与 情形 id 进行删除
     * @param situationId
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSituationByPidAndSituationId")
    public String delSituationByPidAndSituationId(Integer situationId,Integer pid){
        try{
            return situationService.delSituationByPidAndSituationId(situationId,pid);
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败，请重新尝试";
        }
    }
}
