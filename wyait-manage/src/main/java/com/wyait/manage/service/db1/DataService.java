package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.entity.DataAndSiutationVO;
import com.wyait.manage.pojo.Data;
import com.wyait.manage.utils.PageDataResult;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/30 08:51
 */
public interface DataService extends IService<Data> {
    /**
     * 查询材料列表
     * @param data
     * @param page
     * @param limit
     * @return
     */
    PageDataResult getData(Data data, Integer page, Integer limit);

    /**
     * 新增或修改
     * @param data
     * @return
     */
    String addData(Data data);

    String delData(Integer dataId);

    Data selectById(Integer dataId);

    String addBlankUrl(Integer dataId, String blankUrl, String blankName);

    String addTemplateUrl(Integer dataId, String templateUrl, String templateName);

    String delBlankUrl(Integer dataId);

    String delTemplateUrl(Integer dataId);

    List<Data> getDataAll();

    List<Data> getDataByDoooId(Integer doooId);

    String delDataAndDooo(Integer doooId, Integer dataId);

    List<Data> getDataBySituationId(Integer situationId);

    List<DataAndSiutationVO> getDataAndSituationVOById(Integer situationId);

    String delDataAndSituation(Integer situationDetailsId, Integer dataId);

    List<Data> getDataAndSituation(Integer situationDetailsId);

    String setDataAndSituation(Integer dataId, Integer situationId, Integer situationDetailsId);

    List<Data> getTemplateDataByDoooId(Integer doooId);

    String addDataAndSituation(Integer dataId, Integer situationId);

    String delSituationAndData(Integer dataId, Integer situationId);

    List<DataAndSiutationVO> getDataAndSituationVOByIdTow(Integer situationId);

    /**
     * 根据查询绑定的通用材料（套餐）
     * @param dataId
     * @param comboId
     * @return
     */
    String setDataAndCombo(Integer dataId, Integer comboId);

    List<Data> getDataByComboId(Integer comboId);

    String delDataAndCombo(Integer comboId, Integer dataId);

    List<DataAndSiutationVO> getDataAndComboSituationVOById(Integer id);

    List<Data> getDataComboSituationDetails(Integer id);

    String delDataComboSituationDetails(Integer dataId, Integer id);

    List<DataAndSiutationVO> getDataComboSituationVOByIdTow(Integer id);

    String setDataComboSituation(Integer dataId, Integer comboSituationId);

    String delDataComboSituation(Integer dataId, Integer id);

    List<Data> getTemplateDataByComboId(Integer comboId);

    /**
     * 根据事项ID查询材料
     * @param did
     * @return
     */
    List<Data> fetchByDataDooo(Integer did);

    /**
     *  根据 选择的情形查询相应的材料清单
     * @param situationId
     * @param situationDetailsId
     * @return
     */
    List<Data> fetchByDataSituation(String situationId , String situationDetailsId);

    List<Data> fetchByDataCombo(Integer comboId);

    List<Data> fetchByComboSituation(String cstd);
}
