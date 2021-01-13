package com.wyait.manage.service.web2;

import com.wyait.manage.dao.FileSituationDAO;
import com.wyait.manage.dao.SituationDetailsMapper;
import com.wyait.manage.pojo.Combo;
import com.wyait.manage.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileSituationServiceImpl implements FileSituationService{

    @Autowired
    FileSituationDAO fileSituationDAO;
    @Autowired
    SituationDetailsMapper situationDetailsMapper;

    @Override
    public String addFileSituation(String fileId, Integer situationDetailsId) {
        //查询套餐id
        Combo combo = situationDetailsMapper.selectComboIdBySituationDetailsId(situationDetailsId);
        //新增关联关系
        fileSituationDAO.insert(combo.getId(),fileId,situationDetailsId);
        return "ok";
    }

    @Override
    public String deleteuploadByFieldIdAndId(Long fileId, Integer situationDetailsId) {
        fileSituationDAO.delete(fileId,situationDetailsId);
        return "ok";
    }
}
