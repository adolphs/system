package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.utils.PageDataResult;

public interface FormService {

    PageDataResult getFormList(FormField formField, Integer page, Integer limit);

    String addFormField(FormField formField);

    String delFormField(String formFieldId);

    /**
     * 关联表单项
     * @param formFieldId  表单项id
     * @param situationDetailsId  情形id
     * @return
     */
    String updateSituationDetailsIdByFormFieldId(String formFieldId, String situationDetailsId,Integer type);

    String deleteSituationDetailsIdByFormFieldId(String formFieldId);
}
