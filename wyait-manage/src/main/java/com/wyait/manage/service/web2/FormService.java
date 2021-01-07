package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    ResponseResult batchUploadForm(MultipartFile file, HttpServletRequest request,String formMainId,Integer comboId);

    FormField getFormPid(String formId);
}
