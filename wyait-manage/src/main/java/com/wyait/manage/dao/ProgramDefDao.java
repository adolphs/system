package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  省事项库
 * @author ：lp
 * @date ：Created in 2020/9/16 15:17
 * @modified By：
 * @version: 1.0$
 */
public interface ProgramDefDao extends BaseMapper<ProgramDef> {

    /** 通过名称模糊分页查询 */
    List<ProgramDef> fetchAllmatterList(@Param("programName") String programName, @Param("start") Integer start, @Param("end") Integer end);

    /** 查询总数 */
    int getMatterCounts(@Param("programName") String programName);

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

    List<Map<String,Object>> getDoooCodeList(@Param("doooName") String doooName);
}
