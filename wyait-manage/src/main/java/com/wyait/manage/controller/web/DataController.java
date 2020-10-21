package com.wyait.manage.controller.web;

import com.wyait.manage.pojo.Data;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.db1.DataService;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/7/30 08:48
 */
@Controller
@RequestMapping("/data")
public class DataController {
    private static final Logger logger = LoggerFactory
            .getLogger(DataController.class);

    @Autowired
    private DataService dataService;
    @Autowired
    private LogSystemService logSystemService;
    /**
     * 进入材料管理页面
     * @return
     */
    @RequestMapping("/dataList")
    public String dataList(){
        return "data/dataList";
    }

    /**
     * 材料添加页面
     * @return
     */
    @RequestMapping("/dataAdd")
    public ModelAndView dataAdd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("data/dataAdd");
        return view;
    }

    @RequestMapping("/dataUpdate")
    public ModelAndView dataUpdate(Integer dataId){
        ModelAndView view = new ModelAndView();
        view.setViewName("data/dataUpdate");
        view.addObject("data",dataService.selectById(dataId));
        return view;
    }

    /**
     * 根据ID进入详情页
     * @param dataId
     * @return
     */
    @RequestMapping("/dataDetails")
    public ModelAndView dataDetails(Integer dataId){
        ModelAndView view = new ModelAndView();
        view.setViewName("data/dataDetails");
        if (dataId != null){
            Data data = dataService.selectById(dataId);
            view.addObject("data",data);
            view.addObject("dataId",dataId);
        }
        return view;
    }

    /**
     * 分页查询材料支持筛选加分页
     * @param page
     * @param limit
     * @param data
     * @return
     */
    @RequestMapping("/getDataList")
    @ResponseBody
    public PageDataResult getDataList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, Data data){
        logger.debug("分页查询材料列表！搜索条件：dooo：" + data + ",page:" + page
                + ",每页记录数量limit:" + limit);
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取事项管理列表
            pdr = dataService.getData(data, page, limit);
            logger.debug("事项列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }

        return pdr;
    }



    //图片上传测试
    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\mycode\\LayUiTest\\src\\main\\resources\\static\\images\\"+dateStr +"\\"+ originalName;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("jieshi",originalName);
                map2.put("src","/images/"+ dateStr + originalName);

                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }

    /**
     * 新增或者修改
     * @param data
     * @return
     */
    @PostMapping("/addData")
    @ResponseBody
    public String addData(Data data){
        logger.debug("设置材料[新增或更新]！data:" + data );
        try {
            if (null == data) {
                logger.debug("材料[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();

            if (null == existUser) {
                logger.debug("置材料[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
//            logSystemService.addLog(existUser,dooo);
            // 设置用户[新增或更新]
            logger.info("设置材料[新增或更新]成功！data=" + data + "，操作的用户ID=" + existUser.getId());
            return dataService.addData(data);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除
     * @param dataId
     * @return
     */
    @PostMapping("/delData")
    @ResponseBody
    public String delData(Integer dataId){
        logger.debug("删除材料！dataId:" + dataId );
        try{
//            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
//            logSystemService.del(existUser,doooId,2);
            return dataService.delData(dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }

    }

    @ResponseBody
    @RequestMapping("/addBlankUrl")
    public String addBlankUrl(Integer dataId,String blankUrl,String blankName){
        try{
            if (dataId == null){
                return "情形ID不能为空";
            }
            return dataService.addBlankUrl(dataId,blankUrl,blankName);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @ResponseBody
    @RequestMapping("/delBlankUrl")
    public String delBlankUrl(Integer dataId){
        try{
            if (dataId == null){
                return "情形ID不能为空";
            }
            return dataService.delBlankUrl(dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    @ResponseBody
    @RequestMapping("/addTemplateUrl")
    public String addTemplateUrl(Integer dataId,String templateUrl,String templateName){
        try{
            if (dataId == null){
                return "情形ID不能为空";
            }
            return dataService.addTemplateUrl(dataId,templateUrl,templateName);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }

    }

    @ResponseBody
    @RequestMapping("/delTemplateUrl")
    public String delTemplateUrl(Integer dataId){
        try{
            if (dataId == null){
                return "情形ID不能为空";
            }
            return dataService.delTemplateUrl(dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }

    }

    /**
     * 查询全部
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataAll")
    public List<Data> getDataAll(){
        try{
            return dataService.getDataAll();
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }

    }

    /**
     * 根据事项查询绑定的通用材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataByDoooId")
    public List<Data> getDataByDoooId(Integer doooId){
        try{
            return dataService.getDataByDoooId(doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }

    }


    /**
     * 根据事项删除绑定的通用材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDataAndDooo")
    public String delDataAndDooo(Integer doooId,Integer dataId){
        try{
            return dataService.delDataAndDooo(doooId,dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }
    }

    /**
     * 根据选项删除绑定的通用材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDataAndSituation")
    public String delDataAndSituation(Integer situationDetailsId,Integer dataId){
        try{
            return dataService.delDataAndSituation(situationDetailsId,dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return "设置材料[删除]异常";
        }
    }

    /**
     * 根据事项查询绑定的情形材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTemplateDataByDoooId")
    public List<Data> getTemplateDataByDoooId(Integer doooId){
        try{
            return dataService.getTemplateDataByDoooId(doooId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询情形材料异常！", e);
            return null;
        }
    }

    /**
     * 根据选项查询绑定的通用材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataAndSituation")
    public List<Data> getDataAndSituation(Integer situationDetailsId){
        try{
            return dataService.getDataAndSituation(situationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }
    }

    /**
     * 根据查询绑定的通用材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/setDataAndSituation")
    public String setDataAndSituation(Integer dataId,Integer situationId,Integer situationDetailsId){
        try{
            return dataService.setDataAndSituation(dataId,situationId,situationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }

    }

    /**
     * 情形的材料情形添加材料
     * @param situationId
     * @param dataId
     * @return
     */
    @ResponseBody
    @RequestMapping("/addDataAndSituation")
    public String addDataAndSituation(Integer situationId,Integer dataId){
        try{
            return dataService.addDataAndSituation(dataId,situationId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("新增材料[删除]异常！", e);
            return null;
        }
    }

    /**
     * 情形的材料情形添加材料
     * @param situationId
     * @param dataId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSituationAndData")
    public String delSituationAndData(Integer situationId,Integer dataId){
        try{
            return dataService.delSituationAndData(dataId,situationId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("新增材料[删除]异常！", e);
            return null;
        }
    }

    /**
     * 根据查询绑定的通用材料（套餐）
     * @param dataId
     * @param comboId
     * @return
     */
    @ResponseBody
    @RequestMapping("/setDataAndCombo")
    public String setDataAndCombo(Integer dataId,Integer comboId){
        try{
            return dataService.setDataAndCombo(dataId,comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置材料[删除]异常！", e);
            return null;
        }

    }

    /**
     * 根据事项查询绑定的情形材料
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataByComboId")
    public List<Data> getDataByComboId(Integer comboId){
        try{
            return dataService.getDataByComboId(comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询情形材料异常！", e);
            return null;
        }
    }

    /**
     * 删除套餐的通用材料
     * @param comboId
     * @param dataId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDataAndCombo")
    public String delDataAndCombo(Integer comboId,Integer dataId){
        try{
            return dataService.delDataAndCombo(comboId,dataId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询情形材料异常！", e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/getDataComboSituationDetails")
    public List<Data> getDataComboSituationDetails(Integer id){
        try{
            return dataService.getDataComboSituationDetails(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询情形材料异常！", e);
            return null;
        }
    }

    /**
     * 删除套餐选项与材料的关联关系
     * @param dataId
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDataComboSituationDetails")
    public String delDataComboSituationDetails(Integer dataId,Integer id){
        try{
            return dataService.delDataComboSituationDetails(dataId,id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询情形材料异常！", e);
            return "查询情形材料异常";
        }
    }

    @RequestMapping("/setDataComboSituation")
    @ResponseBody
    public String setDataComboSituation(Integer dataId,Integer comboSituationId){
        try{
            return dataService.setDataComboSituation(dataId,comboSituationId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("绑定情形材料异常！", e);
            return "绑定情形材料异常";
        }
    }

    @ResponseBody
    @RequestMapping("/delDataComboSituation")
    public String delDataComboSituation(Integer id,Integer dataId){
        try{
            return dataService.delDataComboSituation(dataId,id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除情形材料异常！", e);
            return "删除情形材料异常";
        }
    }

    @RequestMapping("getTemplateDataByComboId")
    @ResponseBody
    public List<Data> getTemplateDataByComboId(Integer comboId){
        try{
            return dataService.getTemplateDataByComboId(comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除情形材料异常！", e);
            return null;
        }
    }
}
