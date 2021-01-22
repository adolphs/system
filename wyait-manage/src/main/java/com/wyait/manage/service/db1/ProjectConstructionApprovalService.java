/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ProjectConstructionApprovalService
 * Author:   Administrator
 * Date:     2021/1/14
 * History:
 */
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
    String getFlowInfoByProjectCategory(String accessToken);


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
}
