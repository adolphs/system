package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.*;
import com.wyait.manage.entity.DoooSituationVOTOW;
import com.wyait.manage.pojo.*;
import com.wyait.manage.pojo.result.PageResponseResult;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.DoooService;
import com.wyait.manage.utils.DocUtils;
import com.wyait.manage.utils.PageDataResult;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 11:28
 */
@Service
public class DoooServiceImpl extends ServiceImpl<DoooMapper, Dooo> implements DoooService {

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
    @Autowired
    private DataMapper dataMapper;
    @Value("${localip}")
    public String localip;


    @Override
    public PageDataResult getDooos(Dooo dooo, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        Integer approvalType = dooo.getApprovalType();
        System.out.println(approvalType);
        List<Dooo> doooList = doooMapper.getDooos(dooo,approvalType);
        // 获取分页查询后的数据
        PageInfo<Dooo> pageInfo = new PageInfo<>(doooList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i=0;i<doooList.size();i++){
            if (doooList.get(i).getApprovalType() == 3){
                doooList.get(i).setApprovalTextName("审批通过");
            }else if(doooList.get( i).getApprovalType() == 1){
                doooList.get(i).setApprovalTextName("待审批");
            }else if(doooList.get(i).getApprovalType() == 2){
                doooList.get(i).setApprovalTextName("审批中");
            }
            Department department = departmentMapper.getDepartmentById(doooList.get(i).getDepartmentId());
            doooList.get(i).setDepartmentName(department.getDepartmentName());
        }
        pdr.setList(doooList);
        return pdr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class })
    public String setDooo(Dooo dooo) {
        if (dooo.getDoooId() != null){
            //判断是否存在相同名称
            Dooo dooo1 = doooMapper.getDoooByNameAndId(dooo.getDoooName(),dooo.getDoooId());
            if (null != dooo1){
                return "该事项名称已经存在";
            }
            //修改
            dooo.setUpdateTime(new Date());
            dooo.setApprovalType(1);
            doooMapper.updateDooo(dooo);
        }else{
            //判断是否存在相同名称
            Dooo dooo1 = doooMapper.getDoooByName(dooo.getDoooName());
            if (null != dooo1){
                return "该事项名称已经存在";
            }
            dooo.setNewTime(new Date());
            dooo.setUpdateTime(new Date());
            dooo.setApprovalType(1);
            doooMapper.insertDooo(dooo);
        }
        return "ok";
    }

    @Override
    public String delDooo(Integer doooId) {

        List<Situation> situation = situationMapper.getSituationById(doooId);
        for (int i=0;i<situation.size();i++){
            situationDetailsMapper.delSituationDetail(situation.get(i).getSituationId());
            situationMapper.delSituationByGoooId(doooId);
        }

        int i = doooMapper.delDooo(doooId);
        //删除其他
        if (i == 1){
            return "ok";
        }else {
            return "删除失败";
        }

    }

    @Override
    public List<Dooo> getDoooList(Integer departmentId) {
        return doooMapper.getDoooList(departmentId);
    }

    @Override
    public Dooo selectById(Integer id) {
        return doooMapper.getDoooById(id);
    }

    @Override
    public String addDoooAndData(Integer dataId, Integer doooId) {
        DataDooo dataDooo = doooMapper.getDataDooo(dataId,doooId);
        if (dataDooo != null){
            return "您选择关联的材料已和本事项关联了，请勿重复关联";
        }
        doooMapper.addDoooAndData(dataId,doooId);
        return "ok";
    }

    @Override
    public List<Dooo> fetchByName(Dooo dooo) {
        return doooMapper.fetchByName(dooo);
    }

    @Override
    public Dooo getDoooById(Integer doooId) {
        return doooMapper.getDoooById(doooId);
    }


    @Override
    public String putApprovalType(Integer doooId, Integer approvalType,String approvalText) {

            if (approvalType==1){
                doooMapper.sendBackApprovalType(doooId,approvalType);
            }else if (approvalType == 2){
                doooMapper.putApprovalType(doooId,approvalType,approvalText);
            }else if (approvalType == 3){
                doooMapper.sendBackApprovalType(doooId,approvalType);
            }else if (approvalType == 4){
                doooMapper.passApprovalType(doooId,1,approvalText);
            }

            return "ok";

    }

    @Override
    public ResponseResult APIDoooList(Integer page, Integer size, String keyWord, String type,Integer departmentId) {
        List<Dooo> doooList = doooMapper.APIDoooList(page,size,keyWord,type,departmentId);
        Integer total = doooMapper.APIDoooListCount(keyWord,type,departmentId);
        PageResponseResult result = new PageResponseResult(page,size,total);
        result.setData(doooList);
        result.setCode(200);
        return result;
    }

    @Override
    public ResponseResult queryDoooSituation(Integer doooId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<DoooSituationVOTOW> doooSituationVOTOWS = new ArrayList<>();
        List<Situation> situations = situationMapper.queryDoooSituationList(doooId);
        for (int i = 0 ; i < situations.size(); i++) {
            DoooSituationVOTOW s = new DoooSituationVOTOW();
            s.setSituation(situations.get(i));
            s.setSituationDetailsList(situationDetailsMapper.getSituationDetailsBySituationId(situations.get(i).getSituationId()));
            doooSituationVOTOWS.add(s);
        }
        result.setData(doooSituationVOTOWS);
        result.setErrorMessage(doooMapper.getDoooById(doooId).getDoooName());
        return result;
    }

    @Override
    public ResponseResult querySituationDetailsByPid(Integer situationDetailsId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<DoooSituationVOTOW> doooSituationVOTOWS = new ArrayList<>();
        List<Situation> situations = situationMapper.querySituationDetailsByPid(situationDetailsId);
        for (int i = 0 ; i < situations.size(); i++) {
            DoooSituationVOTOW s = new DoooSituationVOTOW();
            s.setSituation(situations.get(i));
            s.setSituationDetailsList(situationDetailsMapper.getSituationDetailsBySituationId(situations.get(i).getSituationId()));
            doooSituationVOTOWS.add(s);
        }
        result.setData(doooSituationVOTOWS);
        return result;
    }

    @Override
    public ResponseResult queryDoooDataList(String situationDetailsIds, Integer doooId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<Data> datas = new ArrayList<>();
        String dooName = doooMapper.getDoooById(doooId).getDoooName();

        //解析字符串
        String[] ids = situationDetailsIds.split(",");
        List<Data> dataList = dataMapper.getTemplateDataBySituationDetailsIds(ids);
        datas.addAll(dataList);
        result.setData(datas);

        //材料下载路径
        Long docPathName = System.currentTimeMillis();
        this.downMaterials(dataList, docPathName, dooName);
//            String localip = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8077/api/downDoc/?docId=" + docPathName;
        result.setHost(this.localip + "/api/downDoc?docId=" + docPathName);

        result.setErrorMessage(dooName);
        datas.addAll(dataMapper.fetchByDataDooo(doooId));
        return result;
    }

    /***
     * 单件事生成文档
     * @param dataList   材料数据集合
     * @param docPathName   生成的文档名
     * @param dooName   事项名称
     */
    private void downMaterials(List<Data> dataList, Long docPathName, String dooName) {
        List<Map<String,Object>> doooList = new ArrayList<>();
        JSONObject jsonObject = null;
        if (dataList!=null && dataList.size()>0){
            for (Data datl:dataList){
                jsonObject = new JSONObject();
                jsonObject.put("dataName", datl.getDataName());
                jsonObject.put("remarks", datl.getRemarks().replace(" ",""));
                jsonObject.put("dataForm", datl.getDataForm().replace(" ",""));
                doooList.add(jsonObject);
            }
            DocUtils.createWord(doooList, docPathName, dooName);
        }
    }


    @Override
    public ResponseResult getHotDooo(Integer number) {
        if (null == number){
            number = 10;
        }
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(doooMapper.getHotDooo(number));
        return result;
    }

    @Override
    public List<ProgramWindow> getProgramWindowList(String doooId) {
        return doooMapper.getProgramWindowList(doooId);
    }

    @Override
    public String operatingProgramWindow(ProgramWindow programWindow) {
        if (programWindow.getId() != null && !"".equals(programWindow.getId())){
            //修改
            doooMapper.updateProgramWindow(programWindow);
        }else{
            //新增
            doooMapper.insertProgramWindow(programWindow);
        }
        return "ok";
    }


}
