package com.wyait.manage.service.update;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.common.JSONUtils;
import com.wyait.manage.dao.OrganDAO;
import com.wyait.manage.pojo.Organ;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.ProjectConstructionApprovalService;
import com.wyait.manage.utils.PageDataResult;
import groovy.transform.ThreadInterrupt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @auther h_baojian@sina.com
 * @data 2021/2/5 16:19
 */
@Service
public class OrganServiceImpl implements OrganService{

    @Autowired
    OrganDAO organDAO;
    @Autowired
    ProjectConstructionApprovalService projectConstructionApprovalService;

    @Override
    public PageDataResult getOrganList(Organ organ, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Organ> organList = organDAO.getOrganList(organ);
        // 获取分页查询后的数据
        PageInfo<Organ> pageInfo = new PageInfo<>(organList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(organList);
        return pdr;
    }

    @Override
    @Transactional
    public ResponseResult synchronizeOrgan() throws Exception {
        //删除原先的，再进行保存
        organDAO.deleteAll();
        String data = projectConstructionApprovalService.getOrgListEx();
        Map<String, Object> stringObjectMap = JSONUtils.json2map(data);
        List<Organ> organList = (List<Organ>) stringObjectMap.get("data");
        organDAO.synchronizeOrgan(organList);
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setData("同步完成");
        return result;
    }

}
