package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.ConstructionDictionaryDAO;
import com.wyait.manage.entity.BusinessVO;
import com.wyait.manage.pojo.ConstructionDictionary;
import com.wyait.manage.utils.IdWorkerUtils;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructionDictionaryServiceImpl implements ConstructionDictionaryService{

    @Autowired
    private ConstructionDictionaryDAO constructionDictionaryDAO;

    @Override
    public PageDataResult getConstructionDictionaryList(ConstructionDictionary constructionDictionary, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<ConstructionDictionary> mapList = constructionDictionaryDAO.getConstructionDictionaryList(constructionDictionary);
        // 获取分页查询后的数据
        PageInfo<ConstructionDictionary> pageInfo = new PageInfo<>(mapList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(mapList);
        return pdr;
    }

    @Override
    public String addConstructionDictionary(ConstructionDictionary constructionDictionary) {
        if (constructionDictionary.getConstructionDictionaryId() != null){
            //修改
            constructionDictionaryDAO.update(constructionDictionary);
        }else{
            //新增
            constructionDictionary.setConstructionDictionaryId(IdWorkerUtils.getInstance().createUUID());
            constructionDictionaryDAO.insert(constructionDictionary);
        }
        return "ok";
    }

    @Override
    public String delConstructionDictionary(ConstructionDictionary constructionDictionary) {
        constructionDictionaryDAO.delete(constructionDictionary.getConstructionDictionaryId());
        return "ok";
    }
}
