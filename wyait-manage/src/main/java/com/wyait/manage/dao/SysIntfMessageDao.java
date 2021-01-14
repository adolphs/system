/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SysIntfMessageDao
 * Author:   Administrator
 * Date:     2021/1/11
 * History:
 */
package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.SysIntfMessage;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2021/1/11 13:14
 * @since 1.0.0
 */
public interface SysIntfMessageDao extends BaseMapper<SysIntfMessage> {

    /**
     * 根据用户表单ID查询表单数据
     * @param formId
     * @return
     */
    @Select("select fv.*,d.*,fa.form_main_name,ff.form_field_name,fa.form_main_name " +
            " from form_main fa " +
            " INNER JOIN form_field ff ON ff.form_main_id = fa.form_main_id " +
            " inner join form_value fv on fv.form_field_id = ff.form_field_id " +
            " inner join department d on d.department_id = fa.department_id " +
            " where fv.form_business_id=#{formId}")
    List<Map<String, Object>> feachUserFormList(String formId);

    /**
     * 根据用户表单ID查询表单、部门
     * @param formId
     * @return
     */
    @Select("SELECT distinct fa.form_main_id,fa.form_main_name,d.department_id,d.department_name " +
            " FROM  department d " +
            " INNER JOIN form_main fa ON d.department_id = fa.department_id " +
            " INNER JOIN form_field ff ON ff.form_main_id = fa.form_main_id " +
            " INNER JOIN form_value fv ON fv.form_field_id = ff.form_field_id " +
            " WHERE fv.form_business_id =#{formId}")
    List<Map<String , Object>> feachFormDepartment(String formId);


    /**
     *  根据用户表单ID查询材料附件
     * @param formId
     * @return
     */
    @Select("SELECT upf.create_time,upf.file_name AS materiaName,usf.file_name,usf.file_url,usf.file_locahost " +
            " FROM user_file usf " +
            " LEFT JOIN upload_file upf ON upf.file_id = usf.file_id " +
            " WHERE usf.business_id =#{formId}")
    List<Map<String, Object>> feachMaterialList(String formId);
}
