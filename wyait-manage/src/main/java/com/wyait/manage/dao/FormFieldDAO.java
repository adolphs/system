package com.wyait.manage.dao;

import com.wyait.manage.pojo.FormField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormFieldDAO继承基类
 */
@Mapper
public interface FormFieldDAO{

    List<FormField> getFormList(@Param("formField") FormField formField);

    int insert(FormField formField);

    int updateByFormFieldId(@Param("formField")FormField formField);

    int delFormFieldByFormFieldId(String formFieldId);

    int updateSituationDetailsIdByFormFieldId(@Param("formFieldId") String formFieldId,@Param("situationDetailsId") String situationDetailsId);

    int updateSituationDetailsIdByFormFieldId2(Long formFieldId);

    FormField getFormPid(String formId);

    int insertList(List<FormField> fieldList);

    int delFormFieldByFormMainId(String formMainId);
}