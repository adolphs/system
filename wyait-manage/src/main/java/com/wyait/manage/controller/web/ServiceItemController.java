package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.Organ;
import com.wyait.manage.pojo.ServiceItem;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.update.ServiceItemService;
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
 * @data 2021/2/7 10:22
 */

@Controller
@RequestMapping("/serviceItem")
public class ServiceItemController {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiceItemController.class);

    @Autowired
    ServiceItemService serviceItemService;

    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/itemList")
    public String itemList(){
        return "organ/itemList";
    }

    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/approvalItemList")
    public String approvalItemList(){
        return "organ/approvalItemList";
    }

    @RequestMapping("/serviceItemDetails")
    public ModelAndView serviceItemDetails(String carryOutCode){
        ModelAndView view = new ModelAndView();
        //事项
        view.addObject("serviceItem",serviceItemService.queryItemByCarryOutCode(carryOutCode));
        //窗口
        view.addObject("serviceWindow",serviceItemService.queryWindowByCarryOutCode(carryOutCode));
        //法律法规
        view.addObject("legalBasis",serviceItemService.queryLegalBasisByCarryOutCode(carryOutCode));
        //材料列表
        view.addObject("materials",serviceItemService.queryMaterialsByCarryOutCode(carryOutCode));
        //视图
        view.setViewName("organ/itemDetails");
        return view;
    }

    /**
     * 分页查询部门支持筛选加分页
     * @param page
     * @param limit
     * @param item
     * @return
     */
    @RequestMapping("/getItemList")
    @ResponseBody
    public PageDataResult getItemList(Integer page,
                                       Integer limit, ServiceItem item){
        logger.debug("分页查询事项列表！搜索条件：item：" + item + ",page:" + page
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
            pdr = serviceItemService.getItemList(item, page, limit);
            logger.debug("部门列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("部门列表查询异常！", e);
        }

        return pdr;
    }

    /**
     * 同步事项库
     * @return
     */
    @RequestMapping("/synchronizeItem")
    @ResponseBody
    public ResponseResult synchronizeItem(String carryOutCode){
        try{
            return serviceItemService.synchronizeItem(carryOutCode);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(null,500,null,"同步时发出异常，请联系技术人员或者重新尝试");
        }
    }

    /**
     * 查看法律详情
     * @param basisCode
     * @return
     */
    @RequestMapping("/selectLawterm")
    @ResponseBody
    public ResponseResult selectLawterm(String basisCode){
        try{
            return serviceItemService.selectLawterm(basisCode);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(null,500,null,"查看法律法规详情异常，请联系技术人员或者重新尝试");
        }
    }

    @RequestMapping("/updateApprovalType")
    @ResponseBody
    public ResponseResult updateApprovalType(ServiceItem serviceItem){
        try{
            return serviceItemService.updateApprovalType(serviceItem);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(null,500,null,"修改审批状态异常，请联系技术人员或者重新尝试");
        }
    }
}
