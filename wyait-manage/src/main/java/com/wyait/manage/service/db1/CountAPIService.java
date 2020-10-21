package com.wyait.manage.service.db1;

import com.wyait.manage.pojo.result.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/8 16:40
 */
public interface CountAPIService {
    /**
     * 查询套餐数量与涉及到的事项数量
     * @return
     */
    ResponseResult comboAndDoooCount();

    /**
     * 统计套餐访问
     * @param request
     * @return
     */
    ResponseResult addVisitCount(HttpServletRequest request);

    List<Integer> getHotComboIds();

    /**
     * 查询事项统计与月访问量
     * @return
     */
    ResponseResult doooCount();
}
