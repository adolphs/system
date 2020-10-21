package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.LogSystem;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:12
 */
@Controller
@RequestMapping("/logSystem")
public class LogSystemController {

    private static final Logger logger = LoggerFactory
            .getLogger(LogSystemController.class);

    @Autowired
    private LogSystemService logSystemService;

    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/logSystemList")
    public String logSystemList(){
        return "logSystem/logSystem";
    }

    /**
     * 分页查询事项支持筛选加分页
     * @param page
     * @param limit
     * @param logSystem
     * @return
     */
    @RequestMapping("/getLogSystemList")
    @ResponseBody
    public PageDataResult getLogSystemList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, LogSystem logSystem){
        logger.debug("分页查询日志列表！搜索条件：logSystem：" + logSystem + ",page:" + page
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
            pdr = logSystemService.getLogSystemList(logSystem, page, limit);
            logger.debug("事项列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }

        return pdr;
    }

}
