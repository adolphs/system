/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SysIntfMessageService
 * Author:   Administrator
 * Date:     2021/1/14
 * History:
 */
package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.SysIntfMessage;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2021/1/11 13:17
 * @since 1.0.0
 */
public interface SysIntfMessageService extends IService<SysIntfMessage> {

    String distributionData(String formId);


    /**
     * 根据用户表单ID查询表单数据
     * @param formId
     * @return
     */
    List<Map<String, Object>> feachUserFormList(String formId);

    /**
     * 根据用户表单ID查询表单、部门
     * @param formId
     * @return
     */
    List<Map<String , Object>> feachFormDepartment(String formId);

    /**
     * 根据用户表单ID查询材料附件
     * @param formId
     * @return
     */
    List<Map<String, Object>> feachMaterialList(String formId);
}