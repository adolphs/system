package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.FormMain;
import com.wyait.manage.utils.PageDataResult;

public interface FormMainService {
    PageDataResult getFormMainList(FormMain formMain, Integer page, Integer limit);

    String addFormMainField(FormMain formMain);

    String delFormMainField(String formMainId);

    String updateSituationDetailsIdByFormFieldId(String formMainId, String situationDetailsId, Integer type);

    String deleteSituationDetailsIdByFormFieldId(Long formMainId);
}
