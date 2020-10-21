package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.ComboMapper;
import com.wyait.manage.dao.DataMapper;
import com.wyait.manage.dao.SituationMapper;
import com.wyait.manage.entity.DataAndSiutationVO;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db1.DataService;
import com.wyait.manage.utils.PageDataResult;
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
 * @date 2020/7/30 08:51
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements DataService {

    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private SituationMapper situationMapper;
    @Autowired
    public ComboMapper comboMapper;

    @Override
    public PageDataResult getData(Data data, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Data> dataList = dataMapper.getDatas(data);
        // 获取分页查询后的数据
        PageInfo<Data> pageInfo = new PageInfo<>(dataList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i=0;i<dataList.size();i++){
            if (dataList.get(i).getDataType() == 1){
                dataList.get(i).setType("通用材料");
            }else if(dataList.get(i).getDataType() == 2){
                dataList.get(i).setType("情形材料");
            }
        }
        pdr.setList(dataList);
        return pdr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String addData(Data data) {
        if (data.getDataId() != null){
            //判断是否存在相同名称
            Data data1 = dataMapper.getDoooByNameAndId(data.getDataId(),data.getDataName());
            if (null != data1){
                return "该材料名称已经存在";
            }
            data.setUpdateTime(new Date());
            dataMapper.updateData(data);
        }else{
            //判断是否存在相同名称
            Data data1 = dataMapper.getDataByName(data.getDataName());
            if (null != data1){
                return "该材料名称已经存在";
            }
            if (data.getBlankUrl() != null){
                data.setBlankUrl("/材料/"+data.getBlankUrl());
            }
            if (data.getTemplateUrl() != null){
                data.setTemplateUrl("/材料/"+data.getTemplateUrl());
            }
            data.setNewTime(new Date());
            data.setUpdateTime(new Date());
            dataMapper.insertData(data);
        }
        return "ok";
    }

    @Override
    public String delData(Integer dataId) {
        //删除与情形关联关系
        //删除与事项关联关系
        //删除本身
        dataMapper.delData(dataId);
        return "ok";
    }

    @Override
    public Data selectById(Integer dataId) {
        return dataMapper.selectById(dataId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String addBlankUrl(Integer dataId, String blankUrl, String blankName) {
        Data data = new Data();
        data.setDataId(dataId);
        data.setBlankUrl(blankUrl);
        data.setBlankName(blankName);
        data.setUpdateTime(new Date());
        dataMapper.updateData(data);
        return "ok";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class },transactionManager = "testTransactionManager")
    public String addTemplateUrl(Integer dataId, String templateUrl, String templateName) {
        Data data = new Data();
        data.setDataId(dataId);
        data.setTemplateUrl(templateUrl);
        data.setTemplateName(templateName);
        data.setUpdateTime(new Date());
        dataMapper.updateData(data);
        return "ok";
    }

    @Override
    public String delBlankUrl(Integer dataId) {
        dataMapper.delBlankUrl(dataId);
        return "ok";
    }

    @Override
    public String delTemplateUrl(Integer dataId) {
        dataMapper.delTemplateUrl(dataId);
        return "ok";
    }

    @Override
    public List<Data> getDataAll() {
        return dataMapper.getDataAll();
    }

    @Override
    public List<Data> getDataByDoooId(Integer doooId) {
        List<Data> dataList = dataMapper.getDataByDoooId(doooId);
        return dataList;
    }

    @Override
    public String delDataAndDooo(Integer doooId, Integer dataId) {
        int i = dataMapper.delDataAndDooo(doooId,dataId);
        if (i == 1){
            return "ok";
        }
        return "删除失败，请刷新重新删除";
    }

    @Override
    public List<Data> getDataBySituationId(Integer situationId) {
        return dataMapper.getDataBySituationId(situationId);
    }

    @Override
    public List<DataAndSiutationVO> getDataAndSituationVOById(Integer situationId) {
        return dataMapper.getDataAndSituationVOById(situationId);
    }

    @Override
    public String delDataAndSituation(Integer situationDetailsId, Integer dataId) {
        dataMapper.delDataAndSituation(situationDetailsId,dataId);
        return "ok";
    }

    @Override
    public List<Data> getDataAndSituation(Integer situationDetailsId) {
        return dataMapper.getDataAndSituation(situationDetailsId);
    }

    @Override
    public String setDataAndSituation(Integer dataId, Integer situationId, Integer situationDetailsId) {
        String dataIDs = dataMapper.queryDataAndSituation(dataId,situationDetailsId);
        if (dataIDs != null){
            return "本次材料与选项已经绑定";
        }
        dataMapper.setDataAndSituation(dataId,situationId,situationDetailsId);
        return "ok";
    }

    @Override
    public List<Data> getTemplateDataByDoooId(Integer doooId) {
        //查询所有的情形为最终情形的子情形
//        List<Situation> situations = situationMapper.getSituationByIdAndNodesType(doooId);
//        List<Integer> situaionIds = new ArrayList<>();
//        for (int i =0;i<situations.size();i++){
//            situaionIds.add(situations.get(i).getSituationId());
//        }
//        List<Data> dataList = dataMapper.getTemplateDataBySituationIds(situaionIds);
        List<Data> dataList = dataMapper.getTemplateDataByDoooId(doooId);
        return dataList;
    }

    @Override
    public String addDataAndSituation(Integer dataId, Integer situationId) {
        String dataIDs = dataMapper.queryDataAndSituationId(dataId,situationId);
        if (dataIDs != null){
            return "本次材料与该情形已经绑定";
        }
        dataMapper.insertDataSituation(dataId,situationId);
        return "ok";
    }

    @Override
    public String delSituationAndData(Integer dataId, Integer situationId) {
        dataMapper.delSituationAndData(dataId,situationId);
        return "ok";
    }

    @Override
    public List<DataAndSiutationVO> getDataAndSituationVOByIdTow(Integer situationId) {
        return dataMapper.getDataAndSituationVOById2(situationId);
    }


    @Override
    public String setDataAndCombo(Integer dataId, Integer comboId) {
        String dataIDs = dataMapper.queryDataAndData(dataId,comboId);
        if (dataIDs != null){
            return "本次材料与选项已经绑定";
        }
        dataMapper.setDataAndCombo(dataId,comboId);
        return "ok";
    }

    @Override
    public List<Data> getDataByComboId(Integer comboId) {
        return dataMapper.getDataByComboId(comboId);
    }

    @Override
    public String delDataAndCombo(Integer comboId, Integer dataId) {
        return dataMapper.delDataAndCombo(comboId,dataId);
    }

    @Override
    public List<DataAndSiutationVO> getDataAndComboSituationVOById(Integer id) {
        return dataMapper.getDataAndComboSituationVOById(id);
    }

    @Override
    public List<Data> getDataComboSituationDetails(Integer id) {
        return dataMapper.getDataComboSituationDetails(id);
    }

    @Override
    public String delDataComboSituationDetails(Integer dataId, Integer id) {
        dataMapper.delDataComboSituationDetails(dataId,id);
        return "ok";
    }

    @Override
    public List<DataAndSiutationVO> getDataComboSituationVOByIdTow(Integer id) {
        return dataMapper.getDataComboSituationVOByIdTow(id);
    }

    @Override
    public String setDataComboSituation(Integer dataId, Integer comboSituationId) {
        Integer dataId1 = dataMapper.getDataComboSituation(dataId,comboSituationId);
        if (dataId1 != null){
            return "您已绑定同名的材料了，请重新选择";
        }
        dataMapper.setDataAndComboSituation(dataId,comboSituationId,null);
        return "ok";
    }

    @Override
    public String delDataComboSituation(Integer dataId, Integer id) {
        dataMapper.delDataComboSituation(dataId,id);
        return "ok";
    }

    @Override
    public List<Data> getTemplateDataByComboId(Integer comboId) {
        return dataMapper.getTemplateDataByComboId(comboId);
    }

    @Override
    public List<Data> fetchByDataDooo(Integer did) {
        return dataMapper.fetchByDataDooo(did);
    }

    @Override
    public List<Data> fetchByDataSituation(String situationId, String situationDetailsId) {
        return dataMapper.fetchByDataSituation(situationId,situationDetailsId);
    }

    @Override
    public List<Data> fetchByDataCombo(Integer comboId) {
        return dataMapper.fetchByDataCombo(comboId);
    }

    @Override
    public List<Data> fetchByComboSituation(String cstd) {
        return dataMapper.fetchByComboSituation(cstd);
    }


}
