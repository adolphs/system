package com.wyait.manage.controller.api;

import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.CountAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/8 16:36
 */

@RequestMapping("/api/count")
@RestController
public class CountAPIController {

    @Autowired
    CountAPIService countAPIService;

    /**
     * 查询套餐数量与月访问量
     * @return
     */
    @GetMapping("/comboAndDoooCount")
    public ResponseResult comboAndDoooCount(){
        //统计套餐数量
        try{
            return countAPIService.comboAndDoooCount();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500, "查询套餐数量异常", null);
        }
    }

    /**
     * 统计套餐访问
     * @param request
     * @return
     */
    @PostMapping("/addVisitCount")
    public ResponseResult addVisitCount(HttpServletRequest request){
        //统计套餐数量
        try{
            return countAPIService.addVisitCount(request);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500, "查询套餐数量异常", null);
        }
    }

    /**
     * 查询事项统计与月访问量
     * @return
     */
    @GetMapping("/doooCount")
    public ResponseResult doooCount(){
        //统计套餐数量
        try{
            return countAPIService.doooCount();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500, "查询套餐数量异常", null);
        }
    }
}
