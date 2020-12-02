package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.SysIntfParameterDAO;
import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysIntfParameterServiceImpl implements SysIntfParameterService {

    @Autowired
    private SysIntfParameterDAO sysIntfParameterDAO;

    @Override
    public PageDataResult getSysIntfParameterList(SysIntfParameter sysIntfParameter, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<SysIntfParameter> sysIntfParameterList = sysIntfParameterDAO.getSysIntfParameterList(sysIntfParameter);
        // 获取分页查询后的数据
        PageInfo<SysIntfParameter> pageInfo = new PageInfo<>(sysIntfParameterList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(sysIntfParameterList);
        return pdr;
    }

    @Override
    public String addSysIntfParameter(SysIntfParameter sysIntfParameter, User existUser) {
        if (sysIntfParameter.getId() == null){
            //不存在ID则是新增
            sysIntfParameter.setUserId(existUser.getId());
            sysIntfParameter.setUserName(existUser.getUsername());
            sysIntfParameter.setCreateDate(new Date());
            sysIntfParameter.setModifyDate(new Date());
            sysIntfParameterDAO.insert(sysIntfParameter);
        }else{
            sysIntfParameter.setUserId(existUser.getId());
            sysIntfParameter.setUserName(existUser.getUsername());
            sysIntfParameter.setModifyDate(new Date());
            sysIntfParameterDAO.updateByPrimaryKeySelective(sysIntfParameter);
        }
        return "ok";
    }
}
