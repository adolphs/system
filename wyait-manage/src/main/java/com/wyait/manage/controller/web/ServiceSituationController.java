package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.ServiceItem;
import com.wyait.manage.pojo.ServiceMaterialsSituation;
import com.wyait.manage.pojo.ServiceSituation;
import com.wyait.manage.pojo.ServiceSituationDetails;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.update.ServiceItemService;
import com.wyait.manage.service.update.ServiceSituationService;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/7 16:47
 */
@Controller
@RequestMapping("/serviceSituation")
public class ServiceSituationController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceItemController.class);
    @Autowired
    ServiceSituationService serviceSituationService;
    @Autowired
    ServiceItemService serviceItemService;

    /**
     * 进入情形详情
     * @param situationId  情形ID
     * @return
     */
    @RequestMapping("/getSituationDetails")
    public ModelAndView getSituationDetails(String situationId){
        ModelAndView view = new ModelAndView();
        //情形
        ServiceSituation situation = serviceSituationService.getSituationById(situationId);
        view.addObject("situation",situation);
        //事项
        view.addObject("item",serviceItemService.queryItemByCarryOutCode(situation.getDoooId()));
        //情形选项
        view.addObject("situationDetails",serviceSituationService.getSituationDetailsBySituationId(situationId));
        view.setViewName("organ/situationDetails");
        return view;
    }

    /**
     * 分页查询部门支持筛选加分页
     * @param page
     * @param limit
     * @param situation
     * @return
     */
    @RequestMapping("/getSituationList")
    @ResponseBody
    public PageDataResult getSituationList(Integer page,
                                      Integer limit, ServiceSituation situation){
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
            // 获取部门管理列表
            pdr = serviceSituationService.getSituationList(situation, page, limit);
            logger.debug("部门列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("部门列表查询异常！", e);
        }

        return pdr;
    }

    /**
     * 新增/修改情形
     * @param situation
     * @return
     */
    @RequestMapping("/addSituation")
    @ResponseBody
    public ResponseResult addSituation(ServiceSituation situation){
        try {
            return serviceSituationService.addSituation(situation);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseResult(500,"","新增失败，请联系开发人员");
        }
    }

    /**
     * 删除情形
     * @param situation
     * @return
     */
    @RequestMapping("/delSituation")
    @ResponseBody
    public ResponseResult delSituation(ServiceSituation situation){
        try {
            return serviceSituationService.delSituation(situation.getSituationId());
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseResult(500,"","新增失败，请联系开发人员");
        }
    }

    /**
     * 新增/修改情形选项
     * @param situationDetails
     * @return
     */
    @RequestMapping("/addSituationDetails")
    @ResponseBody
    public ResponseResult addSituationDetails(ServiceSituationDetails situationDetails){
        try {
            return serviceSituationService.addSituationDetails(situationDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseResult(500,"","新增失败，请联系开发人员");
        }
    }

    /**
     * 删除情形详情
     * @param situation
     * @return
     */
    @RequestMapping("/delSituationDetails")
    @ResponseBody
    public ResponseResult delSituationDetails(ServiceSituationDetails situation) {
        try {
            return serviceSituationService.delSituationDetails(situation.getSituationDetailsId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "新增失败，请联系开发人员");
        }
    }

    /**
     * 根据pid寻找情形
     * @param pid
     * @return
     */
    @RequestMapping("/querySituationByPiD")
    @ResponseBody
    public ResponseResult querySituationByPiD(String pid){
        try {
            return serviceSituationService.querySituationByPiD(pid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "查询失败，请联系开发人员");
        }
    }

    /**
     * 查询事项相关的情形
     * @param carryOutCode
     * @return
     */
    @RequestMapping("/queryMaterialsList")
    @ResponseBody
    public ResponseResult queryMaterialsList(String carryOutCode){
        try {
            return serviceSituationService.queryMaterialsList(carryOutCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "查询失败，请联系开发人员");
        }
    }

    /**
     * 绑定材料和情形详情
     * @param serviceMaterialsSituation
     * @return
     */
    @RequestMapping("/addMaterialsAndSituationDetails")
    @ResponseBody
    public ResponseResult addMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation){
        try {
            return serviceSituationService.addMaterialsAndSituationDetails(serviceMaterialsSituation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "绑定失败，请联系开发人员");
        }
    }

    /**
     * 解除绑定材料和情形详情
     * @param serviceMaterialsSituation
     * @return
     */
    @RequestMapping("/delMaterialsAndSituationDetails")
    @ResponseBody
    public ResponseResult delMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation){
        try {
            return serviceSituationService.delMaterialsAndSituationDetails(serviceMaterialsSituation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "解除绑定失败，请联系开发人员");
        }
    }

    /**
     * 根据情形选项id查看相关绑定的材料
     * @param serviceMaterialsSituation
     * @return
     */
    @RequestMapping("/selMaterialsAndSituationDetails")
    @ResponseBody
    public ResponseResult selMaterialsAndSituationDetails(ServiceMaterialsSituation serviceMaterialsSituation){
        try {
            return serviceSituationService.selMaterialsAndSituationDetails(serviceMaterialsSituation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, "", "查询失败，请联系开发人员");
        }
    }

}
