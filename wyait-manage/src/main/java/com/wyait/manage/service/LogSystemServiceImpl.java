package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.*;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/3 20:59
 */
@Service
public class LogSystemServiceImpl extends ServiceImpl<LogSystemMapper, LogSystem> implements LogSystemService {

    @Autowired
    private LogSystemMapper logSystemMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DoooMapper doooMapper;
    @Autowired
    SituationMapper situationMapper;
    @Autowired
    ComboMapper comboMapper;
    @Override
    public PageDataResult getLogSystemList(LogSystem logSystem, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<LogSystem> logSystemList = logSystemMapper.getLogSystemList(logSystem);
        // 获取分页查询后的数据
        PageInfo<LogSystem> pageInfo = new PageInfo<>(logSystemList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(logSystemList);
        return pdr;
    }

    /**
     * 存入数据
     * @param existUser
     * @param department
     * @param i  根据
     * @return
     */
    @Override
    public String addLog(User existUser, Department department) {
        LogSystem logSystem = new LogSystem();
        if(department.getDepartmentId() == null){
            //新增
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(1);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）新增了一个部门，命名为："+department.getDepartmentName());
            logSystemMapper.insert(logSystem);
        }else{
            //修改
            Department department2 = departmentMapper.getDepartmentById(department.getDepartmentId());
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(1);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）修改了一个部门，原名："+ department2.getDepartmentName() +",现更名命名为："+department.getDepartmentName());
            logSystemMapper.insert(logSystem);
        }

        return null;
    }

    @Override
    public String addLog(User existUser, Dooo dooo) {
        LogSystem logSystem = new LogSystem();
        if(dooo.getDoooId() == null){
            //新增
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(2);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）新增了一个事项，命名为："+dooo.getDoooName());
            logSystemMapper.insert(logSystem);
        }else{
            //修改
            Dooo dooo1 = doooMapper.getDoooById(dooo.getDoooId());
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(2);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）修改了一个事项，原名："+ dooo1.getDoooName() +",现更名命名为："+dooo.getDoooName());
            logSystemMapper.insert(logSystem);
        }
        return null;
    }

    @Override
    public String addLog(User existUser, Situation situation) {
        LogSystem logSystem = new LogSystem();
        if(situation.getSituationId() == null){
            //新增
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(3);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）新增了一个情形，命名为："+situation.getSituationDescribe());
            logSystemMapper.insert(logSystem);
        }else{
            //修改
            Situation situation1 = situationMapper.getSituationId(situation.getSituationId());
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(3);
//            logSystem.setContent("用户（"+logSystem.getUserName()+"）修改了一个情形，原名："+ situation1.getSituationDescribe() +",现更名命名为："+situation.getSituationDescribe() );
            logSystemMapper.insert(logSystem);
        }
        return null;
    }

    @Override
    public String addLog(User existUser, Combo combo) {
        LogSystem logSystem = new LogSystem();
        if(combo.getId() == null){
            //新增
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(4);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）新增了一个套餐，命名为："+combo.getComboName());
            logSystemMapper.insert(logSystem);
        }else{
            //修改
            Combo combo1 = comboMapper.getComboById(combo.getId());
            logSystem.setUserId(existUser.getId());
            logSystem.setUserName(existUser.getUsername());
            logSystem.setNewTime(new Date());
            logSystem.setType(4);
            logSystem.setContent("用户（"+logSystem.getUserName()+"）修改了一个套餐，原名："+ combo1.getComboName() +",现更名命名为："+combo.getComboName() );
            logSystemMapper.insert(logSystem);
        }
        return null;
    }

    @Override
    public String del(User existUser, Integer id, int i) {
        LogSystem logSystem = new LogSystem();
        logSystem.setUserId(existUser.getId());
        logSystem.setNewTime(new Date());
        if (i==1){
            //部门
            Department department = departmentMapper.getDepartmentById(id);
            logSystem.setType(i);
            logSystem.setContent("用户（"+existUser.getUsername()+"）删除了部门："+department.getDepartmentName());
        }else if(i == 2){
            //事项
            Dooo dooo = doooMapper.getDoooById(id);
            logSystem.setType(i);
            logSystem.setContent("用户（"+existUser.getUsername()+"）删除了事项："+dooo.getDoooName());
        }else if(i==3){
            //情形
            Situation situation = situationMapper.getSituationId(id);
            logSystem.setType(i);
            logSystem.setContent("用户（"+existUser.getUsername()+"）删除了事项情形："+situation.getSituationDescribe());
        }else if(i==4){
            //套餐
            Combo combo = comboMapper.getComboById(id);
            logSystem.setType(i);
            logSystem.setContent("用户（"+existUser.getUsername()+"）删除了套餐："+combo.getComboName());
            logSystemMapper.insert(logSystem);
        }
        return null;
    }
}
