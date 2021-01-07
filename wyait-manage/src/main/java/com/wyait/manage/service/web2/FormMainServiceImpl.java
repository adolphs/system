package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.FormMainDAO;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.FormMain;
import com.wyait.manage.utils.PageDataResult;
import com.wyait.manage.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormMainServiceImpl implements FormMainService{

    @Autowired
    private FormMainDAO formMainDAO;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Override
    public PageDataResult getFormMainList(FormMain formMain, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<FormMain> formList = formMainDAO.getFormMainList(formMain);
        // 获取分页查询后的数据
        PageInfo<FormMain> pageInfo = new PageInfo<>(formList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(formList);
        return pdr;
    }

    @Override
    public String addFormMainField(FormMain formMain) {
        if (formMain.getFormMainId() == null) {
            //新增
            formMain.setFormMainId(snowflakeIdWorker.nextId()+"");
            formMainDAO.insert(formMain);
        }else{
            //修改
            formMainDAO.update(formMain);
        }
        return "ok";
    }

    @Override
    public String delFormMainField(String formMainId) {
        formMainDAO.delete(formMainId);
        return "ok";
    }

    @Override
    public String updateSituationDetailsIdByFormFieldId(String formMainId, String situationDetailsId, Integer type) {
        formMainDAO.updateSituationDetailsIdByFormFieldId(formMainId,situationDetailsId);
        return "ok";
    }

    @Override
    public String deleteSituationDetailsIdByFormFieldId(Long formMainId) {
        formMainDAO.deleteSituationDetailsIdByFormFieldId(formMainId);
        return "ok";
    }


}
