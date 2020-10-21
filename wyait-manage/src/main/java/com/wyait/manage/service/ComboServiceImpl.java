package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.ComboMapper;
import com.wyait.manage.dao.ComboSiuationMapper;
import com.wyait.manage.dao.DataMapper;
import com.wyait.manage.dao.SituationDetailsMapper;
import com.wyait.manage.entity.*;
import com.wyait.manage.pojo.*;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.ComboService;
import com.wyait.manage.service.db1.CountAPIService;
import com.wyait.manage.utils.DocUtils;
import com.wyait.manage.utils.PageDataResult;
import com.wyait.manage.utils.RedisUtils;
import net.sf.json.JSONObject;
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
 * @date 2020/7/3 21:00
 */

@Service
public class ComboServiceImpl extends ServiceImpl<ComboMapper, Combo> implements ComboService {

    @Autowired
    private ComboMapper comboMapper;
    @Autowired
    private SituationDetailsMapper situationDetailsMapper;
    @Autowired
    private ComboSiuationMapper comboSiuationMapper;
    @Autowired
    DataMapper dataMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    CountAPIService countAPIService;
    @Autowired
    private ComboService comboService;

    @Value("${localip}")
    public String localip;


    @Override
    public PageDataResult getComboList(Combo combo, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<ComboVo> comboList = comboMapper.getCombos(combo);
        // 获取分页查询后的数据
        PageInfo<ComboVo> pageInfo = new PageInfo<>(comboList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i=0;i<comboList.size();i++){
            List<ComboDooo> comboDoooList = comboMapper.getDoooCount(comboList.get(i).getId());
            int departmentCount = comboMapper.getDepartmentCount(comboList.get(i).getId());
            comboList.get(i).setDoooCount(comboDoooList.size());
            comboList.get(i).setDepartmentCount(departmentCount);
            if (comboList.get(i).getApprovalType() == 3){
                comboList.get(i).setApprovalTypeName("审批通过");
            }else if(comboList.get( i).getApprovalType() == 1){
                comboList.get(i).setApprovalTypeName("待审批");
            }else if(comboList.get(i).getApprovalType() == 2){
                comboList.get(i).setApprovalTypeName("审批中");
            }
        }
        pdr.setList(comboList);
        return pdr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String setCombo(Combo combo) {
        combo.setNewTime(new Date());
        combo.setUpdateTime(new Date());
        combo.setApprovalType(1);
        int i = comboMapper.insert(combo);
        if ( null != combo.getDoooId()){
            Combo combo1 = comboMapper.getComboByTime();
            ComboDooo comboDooo = new ComboDooo(combo1.getId(),combo.getDoooId());
            i = comboMapper.insertComboDooo(comboDooo);
        }
        //删除redis缓存
        redisUtils.remove("comboList");
        if (i != 0){
            return "ok";
        }else{
            return "新增失败";
        }

    }

    @Override
    public String delCombo(Integer comboId) {
        //删除缓存
        redisUtils.remove("comboList");
        //先删除其他东西
        comboMapper.delComboDooo(comboId);
        //在删除本体
        int i = comboMapper.delCombo(comboId);
        if (i != 0){
            return "ok";
        }else{
            return "删除失败";
        }
    }

    @Override
    public ComboDetailsVo getComboDetailsVo(Integer id,Integer departmentId) {
        ComboDetailsVo comboDetailsVo = new ComboDetailsVo();
        //查出套餐本身
        Combo combo = comboMapper.getComboById(id);
        comboDetailsVo.setComboName(combo.getComboName());
        comboDetailsVo.setType(combo.getType());
        if (combo == null){
            return comboDetailsVo;
        }
        //查出相关事项
        List<Dooo> dooos = comboMapper.getDoooByComboId(id,null);
        //查出所有情形
        if (dooos.size() == 0){
            return comboDetailsVo;
        }
        int[] ids = new int[dooos.size()];

        for(int i = 0; i<dooos.size();i++){
            ids[i] = dooos.get(i).getDoooId();
        }

        List<Situation> situations = comboMapper.getSituaionVObyDoooId(ids);
        List<SituationVO> list = new ArrayList<>();
        for (int i=0;i<situations.size();i++){
            List<SituationDetails> situationDetails = situationDetailsMapper.getSituationDetailsBySituationId(situations.get(i).getSituationId());
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0;j<situationDetails.size();j++){
                stringBuffer.append(situationDetails.get(j).getDetailsDescribe());
                if (j!=situationDetails.size() || j>situationDetails.size()){
                    stringBuffer.append("/");
                }
            }
            SituationVO situationVO = new SituationVO();
            situationVO.setSituationDetailsDescribe(stringBuffer.toString());
            situationVO.setSituationDescribe(situations.get(i).getSituationDescribe());
            list.add(situationVO);
        }
        //查出所有部门
        List<Department> departments = comboMapper.getDepartmentByDoooId(ids);

        comboDetailsVo.setDepartments(departments);

        if (departmentId == null){
            comboDetailsVo.setDooos(dooos);
        }else{
            comboDetailsVo.setDooos(comboMapper.getDoooByComboId(id,departmentId));
        }
        comboDetailsVo.setSituations(list);
        return comboDetailsVo;
    }

    @Override
    public String addDooo(Combo combo) {
        //查询所有与这个套餐关联的事项
        Map<String,Integer> map = comboMapper.getComboDoooByComboId(combo.getId(),combo.getDoooId());
        if (map != null){
            //存在就返回已存在
            return "该事项已经存在该套餐内";
        }
        //如果不存在就添加
        ComboDooo comboDooo = new ComboDooo();
        comboDooo.setCombo_id(combo.getId());
        comboDooo.setDooo_id(combo.getDoooId());
        comboMapper.insertComboDooo(comboDooo);
        return "ok";


    }

    @Override
    public String delComboDooo(ComboDooo comboDooo) {
        int i = comboMapper.delComboDoooByComboIdByDoooId(comboDooo.getCombo_id(),comboDooo.getDooo_id());
        if (i == 0){
            return "删除失败。";
        }
        return "ok";
    }

    @Override
    public String updateComboDooo(Combo combo) {
        combo.setUpdateTime(new Date());
        int i = comboMapper.updateComboDooo(combo);
        if (i == 0){
            return "修改套餐名称失败";
        }
        return "ok";
    }

    @Override
    public PageDataResult getComboSituationList(ComboSituation comboSituation, Integer page, Integer limit) {
        List<ComboSituationVO> comboSituationVOList = comboMapper.getComboSituationList(comboSituation);
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);

        // 获取分页查询后的数据
        PageInfo<ComboSituationVO> pageInfo = new PageInfo<>(comboSituationVOList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i=0;i<comboSituationVOList.size();i++){
            List<ComboSituationDetails> situationDetails = comboMapper.getComboSituationDetailsBySituationId(comboSituationVOList.get(i).getId());
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0;j<situationDetails.size();j++){
                stringBuffer.append(situationDetails.get(j).getDetailsDescribe());
                if (j!=situationDetails.size() || j>situationDetails.size()){
                    stringBuffer.append("/");
                }
            }
            comboSituationVOList.get(i).setDetailsDescribe(stringBuffer.toString());
        }
        pdr.setList(comboSituationVOList);
        return pdr;
    }

    @Override
    public String addComboSituation(Integer comboId, String situationDescribe, String detailsDescribe,Integer id) {
        if (id == null){
            ComboSituation comboSituation = new ComboSituation();
            comboSituation.setComboId(comboId);
            comboSituation.setSituationDescribe(situationDescribe);
            comboSituation.setNewTime(new Date());
            comboSituation.setUpdateTime(comboSituation.getNewTime());
            comboMapper.addComboSituation(comboSituation);
            ComboSituation comboSituation1 = comboMapper.selectComboSituation();
            String[] arr = detailsDescribe.split("/");
            for (int i=0;i<arr.length;i++){
                ComboSituationDetails comboSituationDetails = new ComboSituationDetails();
                comboSituationDetails.setComboSituationId(comboSituation1.getId());
                comboSituationDetails.setDetailsDescribe(arr[i]);
                comboMapper.insestComboSituationDetails(comboSituationDetails);
            }
        }else{
            //原先的删掉
            comboMapper.updateComboSituation(situationDescribe,id);
            comboMapper.delComboSituationDetails(id);
            String[] arr = detailsDescribe.split("/");
            for (int i=0;i<arr.length;i++){
                ComboSituationDetails comboSituationDetails = new ComboSituationDetails();
                comboSituationDetails.setComboSituationId(id);
                comboSituationDetails.setDetailsDescribe(arr[i]);
                comboMapper.insestComboSituationDetails(comboSituationDetails);
            }

        }

        return "ok";
    }

    @Override
    public ComboSituation selectComboSituationById(Integer id) {
        return comboSiuationMapper.selectComboSituationById(id);
    }

    @Override
    public List<ComboSituation> selectPidByComboSituationId(Integer id) {
        return comboSiuationMapper.selectPidByComboSituationId(id);
    }

    @Override
    public String addComboSituationV2(ComboSituation comboSituation) {
        if (comboSituation.getId() == null){
            //ID等null 表示新增
            //第一步，查明数据库是否存在该情形下面是有相同名称的情形
            ComboSituation cs = comboSiuationMapper.selectNameAnId(comboSituation.getPid(),comboSituation.getSituationDescribe());
            if (cs != null){
                return comboSituation.getSituationDescribe()+"情形已存在，请勿重新添加";
            }
            comboSituation.setUpdateTime(new Date());
            comboSituation.setNewTime(new Date());
            comboSiuationMapper.addComboSituationV2(comboSituation);
        }else{
            //ID！等null 表示修改
            ComboSituation cs = comboSiuationMapper.selectNameAnIdById(comboSituation.getPid(),comboSituation.getSituationDescribe(),comboSituation.getId());
            if (cs != null){
                return comboSituation.getSituationDescribe()+"情形已存在，请勿重新添加";
            }
            comboSituation.setUpdateTime(new Date());
            comboSiuationMapper.update(comboSituation);
        }
        return "ok";
    }

    @Override
    public String delComboSituation(Integer id) {
        comboSiuationMapper.delComboSituation(id);
        return "ok";
    }

    @Override
    public List<ComboSituationDetails> selectSituationDetailsByComboSituationId(Integer id) {
        return comboSiuationMapper.selectSituationDetailsByComboSituationId(id);
    }

    @Override
    public String setComboSituationDetails(ComboSituationDetails comboSituationDetails) {
        if (comboSituationDetails.getId() != null){
            //检查是否存在同名的选项
            ComboSituationDetails cd = comboSiuationMapper.queryComboSituationDetailsById(comboSituationDetails.getDetailsDescribe(),comboSituationDetails.getComboSituationId(),comboSituationDetails.getId());
            if (cd != null){
                return "您要修改的选择已存在，请勿重复添加";
            }
            comboSituationDetails.setUpdateTime(new Date());
            comboSiuationMapper.editComboSituationDetails(comboSituationDetails);
        }else{
            //检查是否存在同名的选项
            ComboSituationDetails cd = comboSiuationMapper.queryComboSituationDetails(comboSituationDetails.getDetailsDescribe(),comboSituationDetails.getComboSituationId());
            if (cd != null){
                return "您添加的选项已存在，请勿重复添加";
            }
            comboSituationDetails.setNewTime(new Date());
            comboSiuationMapper.setComboSituationDetails(comboSituationDetails);
        }
        return "ok";
    }

    @Override
    @Transactional(transactionManager = "testTransactionManager")
    public String delComboSituationDetails(Integer id) {
        //删除所有的与选项关联的事情
        dataMapper.delComboSituationDetails(id);
        //删除选项本身
        comboSiuationMapper.delComboSituationDetails(id);
        //删除相关联的情形
        comboSiuationMapper.delComboSituationByPid(id);
        return "ok";
    }

    @Override
    public String setDataAndComboSituation(Integer dataId, Integer comboSituationId, Integer comboSituationDetailsId) {
        //判断是否已存在
        Integer dataId1 = dataMapper.setDataComboSituationQureyDataId(dataId,comboSituationDetailsId);
        if (dataId1 != null){
            return "本次关联材料已与选项绑定,请勿重新选择";
        }
        dataMapper.setDataAndComboSituation(dataId,comboSituationId,comboSituationDetailsId);
        return "ok";
    }

    @Override
    public List<ComboSituationDetails> getCmoboSituationDetails(Integer id) {
        return comboSiuationMapper.getCmoboSituationDetails(id);
    }

    @Override
    public String setSituationTOW(Integer pid, Integer type, String situationDescribe, Integer situationId, Integer situationIdTOW) {
        if (situationIdTOW == null){
            //查询是否重名
            List<ComboSituation> s = comboMapper.getSituationPid(situationId,situationDescribe);
            if (s.size() != 0){
                return "已存在同名称的情形";
            }
            ComboSituation situation = new ComboSituation();
            ComboSituation situation1 = comboMapper.getSituationId(situationId);
            situation.setComboId(situation1.getComboId());
            situation.setPid(pid);
            situation.setType(type);
            situation.setSituationDescribe(situationDescribe);
            situation.setNewTime(new Date());
            comboMapper.addComboSituation(situation);
        }else{
            List<ComboSituation> s = comboMapper.getSituationPidTow(situationId,situationDescribe,situationIdTOW);
            if (s.size() != 0){
                return "已存在同名称的情形";
            }
            ComboSituation situation = new ComboSituation();
            situation.setUpdateTime(new Date());
            situation.setSituationDescribe(situationDescribe);
            situation.setId(situationIdTOW);
            situation.setType(type);
            comboMapper.updateSituation(situation);
        }

        return "ok";
    }

    @Override
    public List<ComboSituation> findSituationByPid(Integer situationDetailsId) {
        return comboSiuationMapper.findSituationByPid(situationDetailsId);
    }

    @Override
    public String delSituationByPidAndSituationId(Integer situationId, Integer pid) {
        comboSiuationMapper.delSituationByPidAndSituationId(situationId,pid);
        return "ok";
    }

    @Override
    public String putApprovalType(Integer comboId, Integer approvalType, String approvalText) {
        if (approvalType==1){
            comboMapper.sendBackApprovalType(comboId,approvalType);
        }else if (approvalType == 2){
            comboMapper.putApprovalType(comboId,approvalType,approvalText);
        }else if (approvalType == 3){
            comboMapper.sendBackApprovalType(comboId,approvalType);
        }else if (approvalType == 4){
            comboMapper.passApprovalType(comboId,1,approvalText);
        }

        return "ok";
    }

    @Override
    public List<Combo> findAllCombo() {
        return comboMapper.findAllCombo();
    }

    @Override
    public List<ComboSituation> findAllComboSituation(Integer comboId) {
        return comboMapper.findAllComboSituation(comboId);
    }

    @Override
    public Map<String, Object> fetchMattersNums() {
        return comboMapper.fetchMattersNums();
    }

    @Override
    public Combo getComboById(Integer id) {
        return comboMapper.getComboById(id);
    }

    @Override
    public ResponseResult APIComboList(String type) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setHost(type);
        //判断redis上面是否拥有对应数据
        boolean hasKey = redisUtils.exists("comboList1");
        if (hasKey){
            //如果有，直接从redis上面获取
            Object comboList =  redisUtils.get("comboList");
            System.out.println("comboList");
            result.setData(comboList);
        }else{
            //如果没有，加载存入redis，并且返回前端
            List<Combo> comboList = comboMapper.APIComboList();
            redisUtils.set("comboList",comboList);
            result.setData(comboList);
        }

        return result;
    }

    @Override
    public ResponseResult queryComboSituation(Integer comboId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<ComboSituationVOTOW> comboSituationVOTOWS = new ArrayList<>();
        List<ComboSituation> comboSituations = comboMapper.findAllComboSituation(comboId);
        for (int i = 0;i<comboSituations.size();i++){
            ComboSituationVOTOW c  =  new ComboSituationVOTOW();
            c.setComboSituation(comboSituations.get(i));
            c.setComboSituationDetailsList(comboMapper.getComboSituationDetailsBySituationId(comboSituations.get(i).getId()));
            comboSituationVOTOWS.add(c);
        }
        result.setData(comboSituationVOTOWS);
        return result;
    }

    @Override
    public ResponseResult queryComboSituationDetailsByPid(Integer comboSituationDetailsId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<ComboSituationVOTOW> comboSituationVOTOWS = new ArrayList<>();
        List<ComboSituation> comboSituations = comboSiuationMapper.findSituationByPid(comboSituationDetailsId);
        for (int i = 0;i<comboSituations.size();i++){
            ComboSituationVOTOW c  =  new ComboSituationVOTOW();
            c.setComboSituation(comboSituations.get(i));
            c.setComboSituationDetailsList(comboMapper.getComboSituationDetailsBySituationId(comboSituations.get(i).getId()));
            comboSituationVOTOWS.add(c);
        }
        result.setData(comboSituationVOTOWS);
        return result;
    }

    @Override
    public ResponseResult queryComboDataList(String comboSituationDetailsIds,Integer comboId) throws InterruptedException {
//        Thread.sleep(10000);
//        System.out.println("进入睡眠状态");
        ResponseResult result = new ResponseResult();
        try{
            result.setCode(200);
            result.setErrorMessage(comboMapper.getComboById(comboId).getComboName());

            //文件下载路径
            Long docName = System.currentTimeMillis();
            downMaterials(comboSituationDetailsIds, comboId,docName);
//            String localip = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8077/api/downDoc/?docId=" + docName;
//            String localip = "http://122.51.167.169:8077/api/downDoc?docId=" + docName;
            String localip = this.localip +"/api/downDoc?docId=" + docName;
            result.setHost(localip);


            List<Data> datas = new ArrayList<>();
            datas.addAll(dataMapper.fetchByDataCombo(comboId));
            //解析字符串
            String[] ids = comboSituationDetailsIds.split(",");
            datas.addAll(dataMapper.getTemplateDataByComboSituationDetailsIds(ids));
            result.setData(datas);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void downMaterials(String comboSituationDetailsIds, Integer comboId, Long docName) {
        Combo combo = comboService.getComboById(comboId);
        List<Data> dataList1 = dataMapper.fetchByDataCombo(comboId);
        List<Data> dataList = dataMapper.getTemplateDataByComboSituationDetailsIds(comboSituationDetailsIds.split(","));
        List<Map<String,Object>> list = new ArrayList<>();
        JSONObject jsonObject = null;
        if (null != dataList){
            for (Data data :dataList1){
                jsonObject = new JSONObject();
                jsonObject.put("dataName", data.getDataName());
                jsonObject.put("remarks", data.getRemarks().replace(" ",""));
                jsonObject.put("dataForm", data.getDataForm().replace(" ",""));
                list.add(jsonObject);
            }

            for (Data doo:dataList){
                jsonObject = new JSONObject();
                jsonObject.put("dataName", doo.getDataName());
                jsonObject.put("remarks", doo.getRemarks().replace(" ",""));
                jsonObject.put("dataForm", doo.getDataForm().replace(" ",""));
                list.add(jsonObject);
            }
            DocUtils.createWord(list,docName,combo.getComboName());
        }
    }

    @Override
    public ResponseResult getAllSituation(Integer comboId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<ComboSituationVOTOW> situationVOTOWS = new ArrayList<>();
        //找出所有情形与答案
        List<ComboSituation> comboSituations = comboMapper.querySituationByComboId(comboId);
        for (int i = 0;i<comboSituations.size();i++){
            List<ComboSituationDetails> comboSituationDetails = comboSiuationMapper.selectSituationDetailsByComboSituationId(comboSituations.get(i).getId());
            ComboSituationVOTOW comboVOTOW = new ComboSituationVOTOW();
            comboVOTOW.setComboSituationDetailsList(comboSituationDetails);
            comboVOTOW.setComboSituation(comboSituations.get(i));
            situationVOTOWS.add(comboVOTOW);
        }
        //找出一级情形与答案
        List<ComboSituationVOTOW> comboSituationVOTOWList = new ArrayList<>();
        for (ComboSituationVOTOW c: situationVOTOWS) {
            if (c.getComboSituation().getSituationLevel() != null && c.getComboSituation().getSituationLevel() == 1){
                comboSituationVOTOWList.add(c);
            }
        }
        //遍历所有的情形和答案去找下一级情形
        forList3(comboSituationVOTOWList,situationVOTOWS);

        result.setData(comboSituationVOTOWList);
        return result;
    }

    @Override
    public ResponseResult getComboUrl(Integer comboId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData(comboMapper.getComboById(comboId).getFlowChartUrl());
        return result;
    }

    @Override
    public ResponseResult getDepartmentByComboId(Integer comboId) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        List<Department> departments = comboMapper.getDepartmentByComboId(comboId);
        result.setData(departments);
        return result;
    }

    @Override
    public ResponseResult getHotCombo() {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        //获取当月热门套餐前十comboID
//        List<Integer> ints = countAPIService.getHotComboIds();
        //根据comboId获取套餐列表
        List<Combo> combos = comboMapper.getComboByIds();
        result.setData(combos);
        return result;
    }


    /**
     * 别问，问就作者也不知道写的是什么
     * @param comboSituationVOTOWList  情形
     * @param situationVOTOWS 数据源
     */
    public void forList3(List<ComboSituationVOTOW> comboSituationVOTOWList,List<ComboSituationVOTOW> situationVOTOWS){
        if (comboSituationVOTOWList != null){
            for (int i = 0;i<comboSituationVOTOWList.size();i++){
                //传数据源与选项列表进去
                //办法完成的时候，这个选择集合里面的所有选项已经填充对应情形
                forList2(comboSituationVOTOWList.get(i).getComboSituationDetailsList(),situationVOTOWS);
                //继续遍历选项中的情形内是否存在多级情形
                for (int j=0;j<comboSituationVOTOWList.get(i).getComboSituationDetailsList().size();j++){
                    forList3(comboSituationVOTOWList.get(i).getComboSituationDetailsList().get(j).getComboSituationVOTOWS(),situationVOTOWS);
                }
            }
        }
    }

    /**
     *
     * @param list   选项集合
     * @param situationVOTOWS  数据源
     */
    public void forList2(List<ComboSituationDetails> list,List<ComboSituationVOTOW> situationVOTOWS){
        for (int j = 0;j<list.size();j++){
            ComboSituationDetails c = list.get(j);
            if (c.getType() != 1){
                List<ComboSituationVOTOW> csv = new ArrayList<>();
                //调用办法封装
                forList(situationVOTOWS,c,csv);
                list.get(j).setComboSituationVOTOWS(csv);
            }
        }
    }

    /**
     *
     * @param situationVOTOWS 所有的情形和答案
     * @param c 选项
     * @param csv 选项的情形，新建即可
     */
    public void forList(List<ComboSituationVOTOW> situationVOTOWS,ComboSituationDetails c,List<ComboSituationVOTOW> csv){
        for (int k = 0;k<situationVOTOWS.size();k++){
            if (situationVOTOWS.get(k).getComboSituation().getPid() != null ){
                if (situationVOTOWS.get(k).getComboSituation().getPid().equals(c.getId())){
                    csv.add(situationVOTOWS.get(k));
                }
            }
        }
    }
}
