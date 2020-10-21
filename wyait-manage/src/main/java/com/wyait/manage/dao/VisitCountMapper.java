package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.VisitCount;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:06
 */
public interface VisitCountMapper extends BaseMapper<VisitCount> {

//    int insert(VisitCount visitCount);

    int countCombo();

    List<Integer> getHotComboIds();

    int getDoooCount();
}
