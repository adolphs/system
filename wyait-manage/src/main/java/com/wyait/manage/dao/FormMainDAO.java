package com.wyait.manage.dao;

import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.FormMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormMainDAO继承基类
 */
@Mapper
public interface FormMainDAO {
    List<FormMain> getFormMainList(FormMain formMain);

    int insert(FormMain formMain);

    int update(FormMain formMain);

    int delete(String formMainId);

    int updateSituationDetailsIdByFormFieldId(@Param("formMainId") String formMainId,@Param("situationDetailsId") String situationDetailsId);

    int deleteSituationDetailsIdByFormFieldId(Long formMainId);
}