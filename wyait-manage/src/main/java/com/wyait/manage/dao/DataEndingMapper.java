package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.DataEnding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/8/13 09:44
 */
public interface DataEndingMapper extends BaseMapper<DataEnding> {
    List<DataEnding> getDatas(DataEnding dataEnding);

    List<DataEnding> selDataEndingNameByName(String data_ending_name);

//    int insert(DataEnding dataEnding);

    List<DataEnding> selDataEndingNameByNameAndId(@Param("name") String data_ending_name,@Param("id") Integer data_ending_id);

    int update(DataEnding dataEnding);

    List<DataEnding> setAll();

    int addDataEndingAndDooo(@Param("dataEndId") Integer dataEndId,@Param("doooId") Integer doooId);

    int delDataEndingAndDooo(@Param("dataEndId") Integer dataEndId,@Param("doooId") Integer doooId);

    List<DataEnding> getDataEndingByDoooId(Integer doooId);

    List<DataEnding> getDataEndingByComboId(Integer id);

    int addDataEndingAndCombo(@Param("dataEndId")Integer dataEndId, @Param("comboId")Integer comboId);


    String delDataEndingAndCombo(@Param("dataEndId")Integer dataEndId, @Param("comboId")Integer comboId);
}
