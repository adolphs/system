package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.Dooo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:31
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    List<Dooo> getDepartments(@Param("department") Department department);

    Department getDepartmentByName(String departmentName);

    int updateDepartment(Department department);

    int insertDepartment(Department department);

    int delDepartment(@Param("departmentId") Integer departmentId);

    List<Department> getAllDepartments();

    Department getDepartmentById(@Param("departmentId")Integer departmentId);

    Department getDepartmentByNameById(@Param("departmentName") String departmentName,@Param("departmentId") Integer departmentId);
}
