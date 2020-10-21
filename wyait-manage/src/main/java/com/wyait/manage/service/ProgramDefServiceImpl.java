package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wyait.manage.dao.*;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db1.ProgramDefService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 本地数据库
 * @author ：lp
 * @date ：Created in 2020/9/22 11:11
 * @modified By：
 * @version: 1.0$
 */
@Service
public class ProgramDefServiceImpl extends ServiceImpl<ProgramDefDao, ProgramDef> implements ProgramDefService {

    private static final Logger logger = LoggerFactory.getLogger(ProgramDefServiceImpl.class);

    @Autowired
    private ProgramDefMapper prograDao1;
    @Autowired
    private ProgramMaterialMapper materialDao1;
    @Autowired
    private ProgramWindowMapper windowDao1;
    @Autowired
    private ProgramLicenseResultMapper resultDao1;
    @Autowired
    private ProgramItemAskMapper itemAskDao1;
    @Autowired
    private ProgramItemRightDutyMapper rightDutyDao1;


    /**
     *  同步省事项库到本地库
     * @param pde       事项信息表
     * @param prw       办事窗口
     * @param prt        审批结果
     * @param piaList   审批咨询、投诉
     */
    @Override
    public String sysMatterIntf(ProgramDef pde, List<ProgramMaterial> materialist, ProgramWindow prw, ProgramLicenseResult prt, List<ProgramItemAsk> piaList,
             List<ProgramItemRightDuty> itemRightDutyList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //            logger.info(sdf.format(new Date()) + "-----------sysMatterIntf-----------" + pde.getProgramId());
        String msg = null;
        try {
            if (pde!=null) {
                //直接使用源对象save
                User existUser = (User) SecurityUtils.getSubject().getPrincipal();
                pde.setCreateDate(new Date());
                pde.setModifyDate(new Date());

                if (pde.getLatestUpdateDate() != null || pde.getReportDate() != null){
                    Date data1 = sdf.parse(pde.getLatestUpdateDate());
                    pde.setLatestUpdateDate(sdf.format(data1));
                    pde.setReportDate(sdf.format(sdf.parse(pde.getReportDate())));
                }
                pde.setInsertUid(existUser.getInsertUid());
                prograDao1.insert(pde);
            }
            /** 办事材料 */
            if (materialist!=null && materialist.size()>0){
                for (ProgramMaterial pml:materialist){
//                    pml.setCreateDate(new Date());
                    materialDao1.insert(pml);
                }
            }
            /** 办事窗口 */
            if (prw!=null){
                windowDao1.insert(prw);
            }
            /** 办理结果 */
            if (prt!=null){
                resultDao1.insert(prt);
            }
            /** 审批咨询，投诉  */
            if (piaList!=null && piaList.size()>0){
                ProgramItemAsk itemAskNew = null;
                for (ProgramItemAsk pia:piaList){
                    itemAskNew = new ProgramItemAsk();
                    itemAskNew.setSeq(pia.getSeq());
                    itemAskNew.setReviewOrgAddr(pia.getReviewOrgAddr());
                    itemAskNew.setReviewOrgPhone(pia.getReviewOrgPhone());
                    itemAskNew.setAdviseWeb(pia.getAdviseWeb());
                    itemAskNew.setBlogWeb(pia.getBlogWeb());
                    itemAskNew.setWechatNum(pia.getWechatNum());
                    itemAskNew.setEmail(pia.getEmail());
                    itemAskNew.setAskType(pia.getAskType());
                    itemAskDao1.insert(itemAskNew);
                }
            }
            /** 权力与义务*/
            if (itemRightDutyList!=null && itemRightDutyList.size()>0){
                for (ProgramItemRightDuty pird:itemRightDutyList){
                    rightDutyDao1.insert(pird);
                }
            }
            msg = "操作成功！";
        } catch (Exception e){
            msg = "同步异常，请联系管理员！";
            e.printStackTrace();
//            logger.error("------同步省事项库异常-----" + e);
        }
        return msg;
    }
}
