package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.entity.DataAndSiutationVO;
import com.wyait.manage.pojo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/27 10:42
 */
public interface DataMapper extends BaseMapper<Data> {

    List<Data> getDatas(@Param("data") Data data);

    Data getDoooByNameAndId(@Param("dataId")Integer dataId,@Param("dataName") String dataName);

    Data getDataByName(@Param("dataName") String dataName);

    int updateData(Data data);

    int insertData(Data data);

    int delData(Integer dataId);

    Data selectById(Integer dataId);

    int delBlankUrl(Integer dataId);

    int delTemplateUrl(Integer dataId);

    List<Data> getDataAll();

    List<Data> getDataByDoooId(Integer doooId);

    int delDataAndDooo(@Param("doooId") Integer doooId,@Param("dataId") Integer dataId);

    List<Data> getDataBySituationId(Integer situationId);

    List<DataAndSiutationVO> getDataAndSituationVOById(Integer situationId);

    int delDataAndSituation(@Param("situationDetailsId") Integer situationDetailsId,@Param("dataId") Integer dataId);

    List<Data> getDataAndSituation(Integer situationDetailsId);

    int setDataAndSituation(@Param("dataId")Integer dataId,@Param("situationId")Integer situationId, @Param("situationDetailsId") Integer situationDetailsId);

    String queryDataAndSituation(@Param("dataId")Integer dataId, @Param("situationDetailsId")Integer situationDetailsId);

    List<Data> getTemplateDataBySituationIds(@Param("situaionIds")List<Integer> situaionIds);

    String queryDataAndSituationId(@Param("dataId")Integer dataId, @Param("situationId")Integer situationId);

    int insertDataSituation(@Param("dataId")Integer dataId, @Param("situationId")Integer situationId);

    int delSituationAndData(@Param("dataId")Integer dataId, @Param("situationId")Integer situationId);

    List<DataAndSiutationVO> getDataAndSituationVOById2(Integer situationId);

    String queryDataAndData(@Param("dataId") Integer dataId,@Param("comboId") Integer comboId);

    int setDataAndCombo(@Param("dataId") Integer dataId,@Param("comboId")Integer comboId);

    List<Data> getDataByComboId(Integer comboId);

    String delDataAndCombo(@Param("comboId")Integer comboId,@Param("dataId") Integer dataId);

    List<DataAndSiutationVO> getDataAndComboSituationVOById(Integer id);

    int delComboSituationDetails(Integer id);

    int setDataAndComboSituation(@Param("dataId") Integer dataId,@Param("comboSituationId") Integer comboSituationId, @Param("comboSituationDetailsId") Integer comboSituationDetailsId);

    Integer setDataComboSituationQureyDataId(@Param("dataId")Integer dataId, @Param("comboSituationDetailsId")Integer comboSituationDetailsId);

    List<Data> getDataComboSituationDetails(Integer id);

    int delDataComboSituationDetails(@Param("dataId")Integer dataId,@Param("id") Integer id);

    List<DataAndSiutationVO> getDataComboSituationVOByIdTow(Integer id);

    Integer getDataComboSituation(@Param("dataId") Integer dataId, @Param("comboSituationId") Integer comboSituationId);

    int delDataComboSituation(@Param("dataId")Integer dataId, @Param("id")Integer id);

    List<Data> getTemplateDataByComboId(@Param("ids") Integer ids);

    List<Data> fetchByDataDooo(@Param("did") Integer did);

    List<Data> fetchByDataSituation(@Param("situationId") String situationId ,@Param("situationDetailsId") String situationDetailsId);

    List<Data> getTemplateDataByDoooId(Integer doooId);

    List<Data> fetchByDataCombo(@Param("comboId") Integer comboId);

    List<Data> fetchByComboSituation(@Param("cstd") String cstd);

    List<Data> getTemplateDataByComboSituationDetailsIds(String[] ids);

    List<Data> getTemplateDataBySituationDetailsIds(String[] ids);

}
