package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.FormFieldDAO;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.utils.PageDataResult;
import com.wyait.manage.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements FormService{

    @Autowired
    FormFieldDAO formFieldDAO;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Override
    public PageDataResult getFormList(FormField formField, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<FormField> formList = formFieldDAO.getFormList(formField);
        // 获取分页查询后的数据
        PageInfo<FormField> pageInfo = new PageInfo<>(formList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(formList);
        return pdr;
    }

    @Override
    public String addFormField(FormField formField) {
        if (formField.getFormFieldId() == null) {
            //新增
            formField.setFormFieldId(snowflakeIdWorker.nextId()+"");
            formFieldDAO.insert(formField);
        }else{
            //修改
            formFieldDAO.updateByFormFieldId(formField);
        }
        return "ok";
    }

    @Override
    public String delFormField(String formFieldId) {
        formFieldDAO.delFormFieldByFormFieldId(formFieldId);
        return "ok";
    }


}
