package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.DataEndingMapper;
import com.wyait.manage.pojo.DataEnding;
import com.wyait.manage.service.db1.DataEndingService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/13 09:34
 */
@Service
public class DataEndingServiceImpl extends ServiceImpl<DataEndingMapper, DataEnding> implements DataEndingService {

    @Autowired
    DataEndingMapper dataEndingMapper;

    @Override
    public PageDataResult getData(DataEnding dataEnding, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<DataEnding> dataList = dataEndingMapper.getDatas(dataEnding);
        // 获取分页查询后的数据
        PageInfo<DataEnding> pageInfo = new PageInfo<>(dataList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i = 0;i<dataList.size();i++){
            if (dataList.get(i).getData_type() == 1) {
                dataList.get(i).setDataTypeName("批文");
            } else {
                dataList.get(i).setDataTypeName("证件");
            }
            if (dataList.get(i).getIs_electronic_license() == 1) {
                dataList.get(i).setIsElectronicLicense("支持电子证照");
            } else {
                dataList.get(i).setIsElectronicLicense("不支持电子证照");
            }
        }
        pdr.setList(dataList);
        return pdr;
    }

    @Override
    public String addDataEnding(DataEnding dataEnding) {
        if (dataEnding.getData_ending_id() == null){
            //新建
            List<DataEnding> dataEndings = dataEndingMapper.selDataEndingNameByName(dataEnding.getData_ending_name());
            if (dataEndings.size() != 0){
                return "已存在同名的文件，请勿重新添加";
            }
            dataEnding.setNew_time(new Date());
            dataEndingMapper.insert(dataEnding);
        }else{
            //修改
            List<DataEnding> dataEndings = dataEndingMapper.selDataEndingNameByNameAndId(dataEnding.getData_ending_name(),dataEnding.getData_ending_id());
            if (dataEndings.size() != 0){
                return "已存在同名的文件，请重新确认后再修改";
            }
            dataEndingMapper.update(dataEnding);
        }
        return "ok";
    }

    @Override
    public List<DataEnding> setAll() {
        return dataEndingMapper.setAll();
    }

    @Override
    public String addDataEndingAndDooo(Integer dataEndId, Integer doooId) {
        dataEndingMapper.addDataEndingAndDooo(dataEndId,doooId);
        return "ok";
    }

    @Override
    public String addDataEndingAndCombo(Integer dataEndId, Integer comboId) {
        dataEndingMapper.addDataEndingAndCombo(dataEndId,comboId);
        return "ok";
    }

    @Override
    public String delDataEndingAndCombo(Integer dataEndId, Integer comboId) {
        return dataEndingMapper.delDataEndingAndCombo(dataEndId,comboId);
    }

    @Override
    public String delDataEndingAndDooo(Integer dataEndId, Integer doooId) {
        dataEndingMapper.delDataEndingAndDooo(dataEndId,doooId);
        return "ok";
    }

    @Override
    public List<DataEnding> getDataEndingByDoooId(Integer id,Integer type) {
        List<DataEnding> dataEndingList = null;
        if (type == 1){
            dataEndingList = dataEndingMapper.getDataEndingByDoooId(id);
        }else{
            dataEndingList = dataEndingMapper.getDataEndingByComboId(id);
        }

        for (int i = 0;i<dataEndingList.size();i++){
            if (dataEndingList.get(i).getData_type() == 1) {
                dataEndingList.get(i).setDataTypeName("批文");
            } else {
                dataEndingList.get(i).setDataTypeName("证件");
            }
            if (dataEndingList.get(i).getIs_electronic_license() == 1) {
                dataEndingList.get(i).setIsElectronicLicense("支持电子证照");
            } else {
                dataEndingList.get(i).setIsElectronicLicense("不支持电子证照");
            }
        }
        return dataEndingList;
    }
}
