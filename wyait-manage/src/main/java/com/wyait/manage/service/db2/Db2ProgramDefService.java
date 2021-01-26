package com.wyait.manage.service.db2;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 省事项库
 * @author ：lp
 * @date ：Created in 2020/9/16 15:18
 * @modified By：
 * @version: 1.0$
 */
public interface Db2ProgramDefService {

    List<ProgramDef> fetchAllmatterList(@Param("programName") String programName, Integer start, Integer end);

    /** 查询总数 */
    int getMatterCounts(String programName);

    /** 通过事项ID查询基础信息 */
    ProgramDef fetchByIdBasicsData(@Param("programId") String programId);

    /** 查材料 */
    List<ProgramMaterial> fetchByIdMatter(@Param("programId") String programId);

    /** 办事窗口 */
    ProgramWindow fetchByIdMaterialWindow(@Param("programId") String programId);

    /** 办理结果 */
    ProgramLicenseResult fetchByIdMaterResult(@Param("programId") String programId);

    /** 审批咨询，投诉  */
    List<ProgramItemAsk> fetchByIdMaterItemAsk(@Param("programId") String programId);

    /*** 权力与义务*/
    List<ProgramItemRightDuty> fetchByIDRightDuty(@Param("programId") String programId);

    /**
     * 根据事项ID查找编码
     * @param doooId
     * @return
     */
    List<Map<String,Object>> getDoooCodeList(Dooo dooo);
}
