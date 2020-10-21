package com.wyait.manage.service;

import com.wyait.manage.dao.ComboMapper;
import com.wyait.manage.dao.DoooMapper;
import com.wyait.manage.dao.VisitCountMapper;
import com.wyait.manage.service.db1.CountAPIService;
import com.wyait.manage.pojo.VisitCount;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/8 16:40
 */
@Service
@SuppressWarnings("all")
public class CountAPIServiceImpl implements CountAPIService {
    @Autowired
    ComboMapper comboMapper;
    @Autowired
    VisitCountMapper visitCountMapper;
    @Autowired
    DoooMapper doooMapper;

    @Override
    public ResponseResult comboAndDoooCount() {
        Map<String,Integer> map = new HashMap<>();
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        //套餐统计
        int countCombo = comboMapper.countCombo();
        map.put("countCombo",countCombo);
        //月办理量统计
        int countDoooByCombo = visitCountMapper.countCombo();
        map.put("countDoooByCombo",countDoooByCombo);
        result.setData(map);
        return result;
    }

    @Override
    public ResponseResult addVisitCount(HttpServletRequest request) {
        VisitCount visitCount = new VisitCount();
        if (request.getHeader("x-forwarded-for") == null) {
            visitCount.setUserIp(request.getRemoteAddr());
        }else{
            visitCount.setUserIp(request.getHeader("x-forwarded-for"));
        }
        visitCount.setVisitCountId(IdWorkerUtils.getInstance().createUUID());
        //判断传过来的是套餐ID还是事项ID
        if (null == request.getParameter("comboId")){
            //套餐id为空就认为是事项咯
            visitCount.setDoooId(Integer.parseInt(request.getParameter("doooId")));
        }else{
            visitCount.setComboId(Integer.parseInt(request.getParameter("comboId")));
        }
        visitCount.setVisitTime(new Date());
        visitCount.setVisitType(Integer.parseInt(request.getParameter("visitType")));
        visitCountMapper.insert(visitCount);
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        return result;
    }

    @Override
    public List<Integer> getHotComboIds() {
        return visitCountMapper.getHotComboIds();
    }

    @Override
    public ResponseResult doooCount() {
        Map<String,Integer> map = new HashMap<>();
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        //事项统计
        int doooCount = doooMapper.getDoooCount();
        map.put("doooCount",doooCount);
        //月办理量统计
        int countDooo = visitCountMapper.getDoooCount();
        map.put("countDooo",countDooo);
        result.setData(map);
        return result;
    }
}
