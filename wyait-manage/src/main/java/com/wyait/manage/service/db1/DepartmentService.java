package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:30
 */
public interface DepartmentService extends IService<Department> {
    /**
     * 部门查询用户列表
     * @param department
     * @param page
     * @param limit
     * @return
     */
    PageDataResult getDepartments(Department department, Integer page, Integer limit);

    String setDepartment(Department department);

    String delDepartment(Integer departmentId);

    List<Department> getAllDepartments();

    String selBydepartmentId(Integer departmentId);

    /**
     * 查询所有部门
     * @return
     */
    ResponseResult getDepartmentAll();

    /**
     * 根据事项ID获取办理地址电话
     * @return
     */
    ResponseResult getDoooContactDetails(Integer doooId);
}
