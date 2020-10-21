package com.wyait.manage.controller.web;

import com.wyait.manage.entity.ComboDetailsVo;
import com.wyait.manage.pojo.*;
import com.wyait.manage.service.db1.ComboService;
import com.wyait.manage.service.db1.DataService;
import com.wyait.manage.service.db1.LogSystemService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date 2020/7/3 21:27
 */
@Controller
@RequestMapping("/combo")
public class ComboController {
    private static final Logger logger = LoggerFactory
            .getLogger(ComboController.class);

    @Autowired
    private LogSystemService logSystemService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private DataService dataService;
    @Value("${wyait.picpath}")
    public String flowChartUrl;
    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/comboList")
    public String comboList(){
        return "combo/comboList";
    }

    /**
     * 进入事项管理页面
     * @return
     */
    @RequestMapping("/approvalComboList")
    public String approvalComboList(){
        return "combo/approvalComboList";
    }

    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping("/comboAdd")
    public String comboAdd(){
        return "combo/comboAdd";
    }

    @RequestMapping("/comboUpdate")
    public ModelAndView comboUpdate(Integer comboId){
        ModelAndView view = new ModelAndView();
        view.addObject("combo",comboService.getComboById(comboId));
        view.setViewName("combo/comboUpdate");
        return view;
    }

    @RequestMapping("/comboDetails")
    public ModelAndView comboDetails(Integer id,Integer departmentId){
        ModelAndView view = new ModelAndView();
        if (id != null){
            ComboDetailsVo comboDetailsVo = comboService.getComboDetailsVo(id,departmentId);
            view.addObject("comboName",comboDetailsVo.getComboName());
            view.addObject("type",comboDetailsVo.getType());
            view.addObject("dooos",comboDetailsVo.getDooos());
            view.addObject("departments",comboDetailsVo.getDepartments());
            view.addObject("situations",comboDetailsVo.getSituations());
            view.addObject("id",id);
        }
        view.setViewName("combo/comDetails");
        return view;

    }

    @RequestMapping("/comboSituationDetails")
    public ModelAndView situationDetails(Integer id){
        ModelAndView view = new ModelAndView();
        if (id != null){
            //查询情形
            ComboSituation comboSituation = comboService.selectComboSituationById(id);
            //下级情形
            List<ComboSituation> comboSituations = comboService.selectPidByComboSituationId(id);
            view.addObject("comboSituation",comboSituation);
            view.addObject("id",id);
            view.addObject("comboId",comboSituation.getComboId());
//            if (comboSituation.getNodesType() == 0){
//                view.setViewName("combo/comboSituationDetailsEnd");
//                //查询所有材料
//                List<DataAndSiutationVO> DataAndSiutationVOs = dataService.getDataAndComboSituationVOById(id);
//                //查询所有选项
//                List<ComboSituationDetails> situationDetails = comboService.selectSituationDetailsByComboSituationId(id);
//                view.addObject("situationDetails",situationDetails);
//                view.addObject("DataAndSiutationVOs",DataAndSiutationVOs);
//            }else if(comboSituation.getNodesType() == 1){
//                view.setViewName("combo/comboSituationDetails");
//                view.addObject("comboSituations",comboSituations);
//            }else{
                //最后是材料情形了
                view.setViewName("combo/comboSituationDetails2");
                //查询所有材料
//                List<DataAndSiutationVO> DataAndSiutationVOs = dataService.getDataComboSituationVOByIdTow(id);
                //查询所有选项
                List<ComboSituationDetails> comboSituationDetails = comboService.getCmoboSituationDetails(id);
                view.addObject("situationDetails",comboSituationDetails);
//                view.addObject("DataAndSiutationVOs",DataAndSiutationVOs);

        }

        return view;
    }

    /**
     * 分页查询事项支持筛选加分页
     * @param page
     * @param limit
     * @param combo
     * @return
     */
    @RequestMapping("/getComboList")
    @ResponseBody
    public PageDataResult getComboList(@RequestParam("page") Integer page,
                                       @RequestParam("limit") Integer limit, Combo combo){
        logger.debug("分页查询套餐列表！搜索条件：combo：" + combo + ",page:" + page
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
            pdr = comboService.getComboList(combo, page, limit);
            logger.debug("套餐列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }
        return pdr;
    }




    @PostMapping("/setCombo")
    @ResponseBody
    public String setCombo(Combo combo){
        logger.debug("设置套餐[新增或更新]！combo:" + combo );
        try {

            if (null == combo) {
                logger.debug("套餐[新增或更新]，结果=请您填写事项信息");
                return "您填写套餐信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置套餐[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            logSystemService.addLog(existUser,combo);
            // 设置用户[新增或更新]
            logger.info("设置套餐[新增或更新]成功！combo=" + combo + "，操作的用户ID=" + existUser.getId());
            return comboService.setCombo(combo);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }


    @PostMapping("/delCombo")
    @ResponseBody
    public String delCombo(Integer comboId){
        logger.debug("删除套餐！comboId:" + comboId );
        try{
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            logSystemService.del(existUser,comboId,4);
            return comboService.delCombo(comboId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }

    }

    @PostMapping("/addDooo")
    @ResponseBody
    public String addDooo(Combo combo){
        return comboService.addDooo(combo);
    }

    @PostMapping("/delComboDooo")
    @ResponseBody
    public String delComboDooo(ComboDooo comboDooo){
        if (comboDooo == null){
            return "检查到数据异常，请重新尝试";
        }else{
            return comboService.delComboDooo(comboDooo);
        }
    }

    @PostMapping("/updateComboDooo")
    @ResponseBody
    public String updateComboDooo(Combo combo){

        return comboService.updateComboDooo(combo);
    }


    /**
     * 分页查询事项支持筛选加分页
     * @param page
     * @param limit
     * @param comboSituation
     * @return
     */
    @RequestMapping("/getComboSituationList")
    @ResponseBody
    public PageDataResult getComboSituationList(@RequestParam("page") Integer page,
                                                @RequestParam("limit") Integer limit, ComboSituation comboSituation,Integer comboId){
        logger.debug("分页查询套餐列表！搜索条件：comboSituation：" + comboSituation + ",page:" + page
                + ",每页记录数量limit:" + limit);
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            comboSituation.setComboId(comboId);
            // 获取事项管理列表
            pdr = comboService.getComboSituationList(comboSituation, page, limit);
            logger.debug("套餐列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
        }
        return pdr;
    }

    @PostMapping("/addComboSituation")
    @ResponseBody
    public String addComboSituation(Integer comboId,Integer id,String situationDescribe,String detailsDescribe){
        try{
            return comboService.addComboSituation(comboId,situationDescribe,detailsDescribe,id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("事项列表查询异常！", e);
            return "添加失败";
        }

    }

    /**
     * 新增或修改套餐情形
     * @param comboSituation
     * @return
     */
    @PostMapping("/addComboSituationAndData")
    @ResponseBody
    public String addComboSituationAndData(ComboSituation comboSituation){
        logger.debug("设置套餐情形[新增或更新]！comboSituation:" + comboSituation );
        try {

            if (null == comboSituation) {
                logger.debug("套餐情形[新增或更新]，结果=请您填写事项信息");
                return "您填写套餐情形信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置套餐情形[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
//            logSystemService.addLog(existUser,combo);
            // 设置用户[新增或更新]
            logger.info("设置套餐[新增或更新]成功！comboSituation=" + comboSituation + "，操作的用户ID=" + existUser.getId());
            return comboService.addComboSituationV2(comboSituation);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除套餐情形
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delComboSituation")
    public String delComboSituation(Integer id){
        logger.debug("删除套餐情形！comboSituationId:" + id );
        try {
            return comboService.delComboSituation(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 新增套餐情形选项
     * @param comboSituationDetails
     * @return
     */
    @ResponseBody
    @RequestMapping("/setComboSituationDetails")
    public String setComboSituationDetails(ComboSituationDetails comboSituationDetails){
        logger.debug("新增套餐情形选项！comboSituationDetails:" + comboSituationDetails );
        try {
            return comboService.setComboSituationDetails(comboSituationDetails);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除套餐选项
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delComboSituationDetails")
    public String delComboSituationDetails(Integer id){
        logger.debug("删除套餐情形选项！comboSituationDetailsId:" + id );
        try {
            return comboService.delComboSituationDetails(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除套餐情形选项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 套餐选项绑定材料
     * @param dataId
     * @param comboSituationId
     * @param comboSituationDetailsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/setDataAndComboSituation")
    public String setDataAndComboSituation(Integer dataId,Integer comboSituationId,Integer comboSituationDetailsId){
        logger.debug("关联套餐选项与材料！dataId:" + dataId+",comboSituationId="+comboSituationId+",comboSituationDetailsId="+comboSituationDetailsId );
        try {
            return comboService.setDataAndComboSituation(dataId,comboSituationId,comboSituationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除套餐情形选项异常！", e);
            return "操作异常，请您稍后再试";
        }
    }


    /**
     * 根据选项添加子情形
     * @param pid
     * @param type
     * @param situationDescribe
     * @param situationId
     * @param situationIdTOW
     * @return
     */
    @ResponseBody
    @RequestMapping("/setSituationTOW")
    public String setSituationTOW(Integer pid,Integer type,String situationDescribe,Integer situationId,Integer situationIdTOW){
        try{
            return comboService.setSituationTOW(pid,type,situationDescribe,situationId,situationIdTOW);
        }catch (Exception e){
            e.printStackTrace();
            return "错误";
        }
    }

    /**
     * 根据选项查看情形
     * @param situationDetailsId
     * @return
     */
    @RequestMapping("/situationDetailsId")
    @ResponseBody
    public List<ComboSituation> findSituationByPid(Integer situationDetailsId){
        try{
            return comboService.findSituationByPid(situationDetailsId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据PId 与 情形 id 进行删除
     * @param situationId
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSituationByPidAndSituationId")
    public String delSituationByPidAndSituationId(Integer situationId,Integer pid){
        try{
            return comboService.delSituationByPidAndSituationId(situationId,pid);
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败，请重新尝试";
        }
    }

    /**
     * 通过审批
     * @param comboId
     * @param approvalType
     * @param approvalText
     * @return
     */
    @ResponseBody
    @RequestMapping("/putApprovalType")
    public String putApprovalType(Integer comboId,Integer approvalType,String approvalText){
        try{
            return comboService.putApprovalType(comboId,approvalType,approvalText);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置事项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    //图片上传测试
    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){
        String prefix="";
        String dateStr="";
        String fileName = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date)+"/";
                fileName = System.currentTimeMillis()+"."+prefix;
                String filepath = this.flowChartUrl + "/liuchengtu/" + dateStr  + fileName;


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
                map2.put("src","/liuchengtu/"+dateStr + fileName);

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

}
