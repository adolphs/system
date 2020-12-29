package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.UserBusinessDAO;
import com.wyait.manage.entity.BusinessDTO;
import com.wyait.manage.entity.BusinessVO;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private UserBusinessDAO userBusinessDAO;

    @Override
    public PageDataResult getBusinessList(BusinessDTO businessDTO, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<BusinessVO> mapList = userBusinessDAO.getBusinessList(businessDTO);
        // 获取分页查询后的数据
        PageInfo<BusinessVO> pageInfo = new PageInfo<>(mapList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(mapList);
        return pdr;
    }
}
