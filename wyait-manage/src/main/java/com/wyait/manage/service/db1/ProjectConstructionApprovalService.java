package com.wyait.manage.service.db1;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 〈广州市工程建设项目联合审批平台对接接口〉
 *
 * @author Administrator
 * @create 2020/12/28 13:51
 * @since 1.0.0
 */
public interface ProjectConstructionApprovalService {

    /**
     * 3.2项目类型接口
     * @return
     */
    String getProjectCategory(String accessToken);


    /**
     * 3.3阶段信息接口
     * @return
     */
    String getFlowInfoByProjectCategory(String accessToken, String type , String category);


    /**
     * 3.4获取阶段共性材料信息接口
     * @return
     */
    String getProjectBusinessMessage(String accessToken, String id, String itemCodes);


    /**
     *  3.5提交网上申报信息接口
     * @param data
     * @return
     */
    String pushInformation(JSONObject data, List<Map<String, Object>> files);

    /**
     * 3.20项目联办进度信息查询接口
     * @param projectCode
     * @param associationNumber
     * @return
     */
    String queryProgress(String projectCode, String associationNumber);

    /**
     * 5.1项目编码核验服务
     *      [根据项目编码获取检验编码是否存在]
     * @param projectCode  项目编码
     * @return   msg等于200时，核验成功；否则失败
     */
    String proofProjectCode(String projectCode);

    /**
     *  根据事项编码/情形（办理项）编码获取申请材料信息
     *    [situation_code 及code和service_code不能同时为空，也不能同时传值；]
     * @param code      事项实施编码
     * @param service   事项内部编码
     * @param situationCode     情形（办理项）编码
     * @param is_nec   是否必要
     * @return    材料列表
     */
    String getMaterialsList(String code, String service, String situationCode, String is_nec);

    /**
     *  根据事项编码/情形（办理项）编码获取事项/情形（办理项）详细信息
     *    [situation_code 及code和service_code不能同时为空，也不能同时传值；]
     * @param code      事项实施编码
     * @param service   事项内部编码
     * @param situationCode     情形（办理项）编码
     * @return    材料列表
     */
    String getItemList(String code, String service, String situationCode);

    /**
     *  根据材料编号和部门组织机构编码获取引用了该材料的事项信息
     * @param inner_code     材料内部编码
     * @param org_code       部门机构编码
     * @return    List<MaterialItemInfo>
     */
    String getMaterialItemInfoList(String inner_code, String org_code);

    String getOrgListEx();
}
