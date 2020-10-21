package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.DepartmentMapper;
import com.wyait.manage.dao.DoooMapper;
import com.wyait.manage.dao.SituationDetailsMapper;
import com.wyait.manage.dao.SituationMapper;
import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.ProgramWindow;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.DepartmentService;
import com.wyait.manage.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:30
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    private static final Logger logger = LoggerFactory
            .getLogger(DoooServiceImpl.class);

    @Autowired
    private DoooMapper doooMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private SituationMapper situationMapper;
    @Autowired
    private SituationDetailsMapper situationDetailsMapper;

    @Override
    public PageDataResult getDepartments(Department department, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Dooo> doooList = departmentMapper.getDepartments(department);
        // 获取分页查询后的数据
        PageInfo<Dooo> pageInfo = new PageInfo<>(doooList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(doooList);
        return pdr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String setDepartment(Department department) {
        if (department.getDepartmentId() != null){
            //判断是否存在相同名称
            Department department1 = departmentMapper.getDepartmentByNameById(department.getDepartmentName(),department.getDepartmentId());
            if (null != department1){
                return "该事项名称已经存在";
            }
            //修改
            department.setUpdateTime(new Date());
            departmentMapper.updateDepartment(department);
        }else{
            //判断是否存在相同名称
            Department department1 = departmentMapper.getDepartmentByName(department.getDepartmentName());
            if (null != department1){
                return "该事项名称已经存在";
            }
            department.setNewTime(new Date());
            department.setUpdateTime(new Date());
            department.setType(0);
            departmentMapper.insertDepartment(department);
        }
        return "ok";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String delDepartment(Integer departmentId) {
        List<Dooo> doooList = doooMapper.getDoooList(departmentId);
        if(doooList.size()!=0){
            return "您这个部门名下还有事项，请删除事项后再删除部门";
        }
        int i = departmentMapper.delDepartment(departmentId);
        if (i == 1){
            return "ok";
        }else {
            return "删除失败";
        }

    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }

    @Override
    public String selBydepartmentId(Integer departmentId) {
        Department department = departmentMapper.getDepartmentById(departmentId);
        return department.getDepartmentName();
    }


    @Override
    public ResponseResult getDepartmentAll() {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(departmentMapper.getAllDepartments());
        return result;
    }

    @Override
    public ResponseResult getDoooContactDetails(Integer doooId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        ProgramWindow programWindow = new ProgramWindow();
        if (doooId == 38){
            programWindow.setAddress("");//窗口地址
            programWindow.setWinName("");//窗口名称
            programWindow.setWorktime("");//办公时间
            programWindow.setTrafficGuide("");//交通指引
            programWindow.setTel("");//联系电话
        }else if (doooId == 55){
            programWindow.setAddress("广州市番禺区亚运大道550号番禺区政务服务中心三楼建设工程大厅");//窗口地址
            programWindow.setWinName("C58-C60号窗口");//窗口名称
            programWindow.setWorktime("周一至周五：上午8:30-12:00 下午2:00-5:00（国家法定节假日除外）");//办公时间
            programWindow.setTrafficGuide("1. 短线及专线。短线：番微公交3路（区政府西门（会议中心站）—新政务服务中心—东湖洲花园站）；专线：番微公交5路（区政府西门—区政府东门—新政务服务中心）。 2.途径新政务中心公交线路（27条）：番1路、番6路、番10路、番16路、番19路、番26路、番29路、番51B路、番68路、番93路、番99路、番100路、番103路、番126路、番140路、番148路、番149路、番151B路、番153路、番160路、番161路、番162路，301路，广番97路、广番97短线，南沙54路、南沙66路。番禺汽车客运站[公交车站]下车步行至番禺区政务服务中心。");//交通指引
            programWindow.setTel("020-84690912");//联系电话
        }else if(doooId == 68){
            programWindow.setAddress("广州市番禺区亚运大道550号番禺区政务服务中心三楼建设工程大厅");//窗口地址
            programWindow.setWinName("C58-C60号窗口");//窗口名称
            programWindow.setWorktime("周一至周五：上午8:30-12:00 下午2:00-5:00（国家法定节假日除外）");//办公时间
            programWindow.setTrafficGuide("1. 短线及专线。短线：番微公交3路（区政府西门（会议中心站）—新政务服务中心—东湖洲花园站）；专线：番微公交5路（区政府西门—区政府东门—新政务服务中心）。 2.途径新政务中心公交线路（27条）：番1路、番6路、番10路、番16路、番19路、番26路、番29路、番51B路、番68路、番93路、番99路、番100路、番103路、番126路、番140路、番148路、番149路、番151B路、番153路、番160路、番161路、番162路，301路，广番97路、广番97短线，南沙54路、南沙66路。番禺汽车客运站[公交车站]下车步行至番禺区政务服务中心。");//交通指引
            programWindow.setTel("020-84690912");//联系电话
        }
        result.setData(programWindow);
        return result;
    }
}
