package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.Organ;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.service.db1.UserService;
import com.wyait.manage.service.update.OrganService;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 15:07
 */
@Controller
@RequestMapping("/organ")
public class OrganController {
    private static final Logger logger = LoggerFactory
            .getLogger(OrganController.class);

    @Autowired
    private OrganService organService;


    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/organList")
    public String organList(){
        return "organ/organList";
    }

    /**
     * 分页查询部门支持筛选加分页
     * @param page
     * @param limit
     * @param organ
     * @return
     */
    @PostMapping("/getOrganList")
    @ResponseBody
    public PageDataResult getOrganList(Integer page,
                                       Integer limit, Organ organ){
        logger.debug("分页查询事项列表！搜索条件：organ：" + organ + ",page:" + page
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
            pdr = organService.getOrganList(organ, page, limit);
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
    @RequestMapping("/synchronizeOrgan")
    @ResponseBody
    public ResponseResult synchronizeOrgan(){
        try{
            return organService.synchronizeOrgan();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(null,500,null,"同步时发出异常，请联系技术人员或者重新尝试");
        }
    }
}
