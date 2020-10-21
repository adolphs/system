package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.DataEnding;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/13 09:34
 */
public interface DataEndingService extends IService<DataEnding> {
    PageDataResult getData(DataEnding dataEnding, Integer page, Integer limit);

    String addDataEnding(DataEnding dataEnding);

    List<DataEnding> setAll();

    String addDataEndingAndDooo(Integer dataEndId, Integer doooId);

    String delDataEndingAndDooo(Integer dataEndId, Integer doooId);

    List<DataEnding> getDataEndingByDoooId(Integer id,Integer type);

    String addDataEndingAndCombo(Integer dataEndId, Integer comboId);

    String delDataEndingAndCombo(Integer dataEndId, Integer comboId);
}
