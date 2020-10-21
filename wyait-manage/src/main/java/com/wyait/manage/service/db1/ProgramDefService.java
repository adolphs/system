package com.wyait.manage.service.db1;

import com.baomidou.mybatisplus.service.IService;
import com.wyait.manage.pojo.*;

import java.util.List;

/**
 * 本地项目服务类
 * @author ：lp
 * @date ：Created in 2020/9/22 11:10
 * @modified By：
 * @version: 1.0$
 */
public interface ProgramDefService extends IService<ProgramDef> {

    /**
     * 省事项库。目录ID
     */
    String sysMatterIntf(ProgramDef pde, List<ProgramMaterial> materialist, ProgramWindow prw, ProgramLicenseResult prt, List<ProgramItemAsk> piaList, List<ProgramItemRightDuty> rightDutyList) ;
}
