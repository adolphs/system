package com.wyait.manage.controller.web2;

import com.wyait.manage.controller.web.DoooController;
import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.web2.SysIntfParameterService;
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

@RequestMapping("/sysIntfParameter")
@Controller
public class SysIntfParameterController {
    private static final Logger logger = LoggerFactory.getLogger(SysIntfParameterController.class);

    @Autowired
    private SysIntfParameterService sysIntfParameterService;

    /**
     * 进入系统接口页面
     * @return
     */
    @RequestMapping("/sysIntfParameterList")
    public String sysIntfParameterList(){
        return "logSystem/sysIntfParameterList";
    }

    /**
     * 分页查询事项支持筛选加分页
     * @param page
     * @param limit
     * @param sysIntfParameter
     * @return
     */
    @RequestMapping("/getSysIntfParameterList")
    @ResponseBody
    public PageDataResult getSysIntfParameterList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, SysIntfParameter sysIntfParameter){
        logger.debug("分页查询系统对接列表！搜索条件：sysIntfParameter：" + sysIntfParameter + ",page:" + page
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
            pdr = sysIntfParameterService.getSysIntfParameterList(sysIntfParameter, page, limit);
            logger.debug("系统对接查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统对接查询异常！", e);
        }

        return pdr;
    }

    /**
     * 新增系统接口   修改系统接口
     * @param sysIntfParameter
     * @return
     */
    @PostMapping("/addSysIntfParameter")
    @ResponseBody
    public String addSysIntfParameter(SysIntfParameter sysIntfParameter){
        logger.debug("设置系统接口[新增或更新]！sysIntfParameter:" + sysIntfParameter );
        try {
            if (null == sysIntfParameter) {
                logger.debug("系统接口[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置事项[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            // 设置用户[新增或更新]
            logger.info("设置系统接口[新增或更新]成功！sysIntfParameter=" + sysIntfParameter + "，操作的用户ID=" + existUser.getId());
            return sysIntfParameterService.addSysIntfParameter(sysIntfParameter,existUser);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }
}
