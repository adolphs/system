package com.wyait.manage.service;

import com.wyait.manage.dao.DoooMapper;
import com.wyait.manage.dao.ProgramDefDao;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db2.Db2ProgramDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 省事项库
 * @author ：lp
 * @date ：Created in 2020/9/16 15:18
 * @modified By：
 * @version: 1.0$
 */
@Service
public class Db2ProgramDefServiceImpl  implements Db2ProgramDefService {
    @Autowired
    private ProgramDefDao programDefDao2;
    @Autowired
    private DoooMapper doooMapper;
    @Override
    public List<ProgramDef> fetchAllmatterList(String programName, Integer start, Integer end) {
        return programDefDao2.fetchAllmatterList(programName,start, end);
    }

    @Override
    public int getMatterCounts(String programName) {
        return programDefDao2.getMatterCounts(programName);
    }

    @Override
    public List<ProgramMaterial> fetchByIdMatter(String programId) {
        return programDefDao2.fetchByIdMatter(programId);
    }


    @Override
    public ProgramDef fetchByIdBasicsData(String programId) {
        return programDefDao2.fetchByIdBasicsData(programId);
    }

    @Override
    public ProgramWindow fetchByIdMaterialWindow(String programId) {
        return programDefDao2.fetchByIdMaterialWindow(programId);
    }

    @Override
    public ProgramLicenseResult fetchByIdMaterResult(String programId) {
        return programDefDao2.fetchByIdMaterResult(programId);
    }

    @Override
    public List<ProgramItemAsk> fetchByIdMaterItemAsk(String programId) {
        return programDefDao2.fetchByIdMaterItemAsk(programId);
    }

    @Override
    public List<ProgramItemRightDuty> fetchByIDRightDuty(String programId) {
        return programDefDao2.fetchByIDRightDuty(programId);
    }

    @Override
    public List<Map<String,Object>> getDoooCodeList(Dooo dooo) {
        if (dooo == null){
            return null;
        }
        return programDefDao2.getDoooCodeList(dooo.getDoooName());
    }

}
