package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.db1.DepartmentService;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.service.db1.UserService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 15:07
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger logger = LoggerFactory
            .getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LogSystemService logSystemService;

    @Autowired
    private UserService userService;

    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/departmentList")
    public String departmentList(){
        return "department/departmentList";
    }

    /**
     * 分页查询部门支持筛选加分页
     * @param page
     * @param limit
     * @param department
     * @return
     */
    @PostMapping("/getDedepartmentList")
    @ResponseBody
    public PageDataResult getDedepartmentList(Integer page,
                                       Integer limit, Department department){
        logger.debug("分页查询事项列表！搜索条件：department：" + department + ",page:" + page
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
            pdr = departmentService.getDepartments(department, page, limit);
            logger.debug("部门列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("部门列表查询异常！", e);
        }


        return pdr;
    }

    @PostMapping("/getDepartment")
    @ResponseBody
    public String getDepartment(Department department){
        logger.debug("设置事项[新增或更新]！department:" + department );
        try {
            if (null == department) {
                logger.debug("置部门[新增或更新]，结果=请您填写事项信息");
                return "您填写部门信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置部门[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            logSystemService.addLog(existUser,department);
            // 设置用户[新增或更新]
            logger.info("设置部门[新增或更新]成功！department=" + department + "，操作的用户ID=" + existUser.getId());
            return departmentService.setDepartment(department);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置用户[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @ResponseBody
    @PostMapping("/delDepartment")
    public String delDepartment(Integer departmentId){
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        logSystemService.del(existUser,departmentId,1);
        logger.debug("删除部门！departmentId:" + departmentId );
        return departmentService.delDepartment(departmentId);
    }

    @ResponseBody
    @GetMapping("/getDepartments")
    public List<User> getDepartments(){
        System.out.println("---------getDepartments------------" + userService.selectById(30));
        return userService.selectList(null);
    }
}
