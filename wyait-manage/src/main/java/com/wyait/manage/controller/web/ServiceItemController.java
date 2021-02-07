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
     * 分页查询部门支持筛选加分页
     * @param page
     * @param limit
     * @param item
     * @return
     */
    @PostMapping("/getItemList")
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
}
