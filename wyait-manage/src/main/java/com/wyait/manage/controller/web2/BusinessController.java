package com.wyait.manage.controller.web2;

import com.wyait.manage.entity.BusinessDTO;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.service.web2.BusinessService;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/business")
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private BusinessService businessService;

    @RequestMapping("/businessList")
    public String businessList(){
        return "business/businessList";
    }

    @RequestMapping("/businessDetails")
    public ModelAndView businessDetails(String businessId){
        ModelAndView view = new ModelAndView();
        view.addObject("businessId",businessId);
        view.setViewName("business/businessDetails");
        return view;
    }

    /**
     * 分页查询业务支持筛选加分页
     * @param page
     * @param limit
     * @param businessDTO
     * @return
     */
    @RequestMapping("/getBusinList")
    @ResponseBody
    public PageDataResult getBusinList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, BusinessDTO businessDTO){
        logger.debug("分页查询表单项列表！搜索条件：sysIntfParameter：" + businessDTO + ",page:" + page
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
            pdr = businessService.getBusinessList(businessDTO, page, limit);
            logger.debug("表单项查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("表单项查询异常！", e);
        }

        return pdr;
    }
}
