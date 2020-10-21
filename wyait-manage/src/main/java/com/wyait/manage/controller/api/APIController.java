package com.wyait.manage.controller.api;

import com.wyait.manage.service.db1.DepartmentService;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.service.db1.ComboService;
import com.wyait.manage.service.db1.DoooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/9/7 15:54
 */
@RequestMapping("/api")
@RestController
public class APIController {

    @Autowired
    DoooService doooService;
    @Autowired
    ComboService comboService;
    @Autowired
    DepartmentService departmentService;

    @Value("${downAddress}")
    public String address;

    /**
     * 获取所有的事项
     * @param page 当前页
     * @param size 当前页大小
     * @param keyWord 关键字
     * @param type 状态: 0.个人 1法人
     * @param departmentId 部门ID
     * @return
     */
    @GetMapping("/doooList")
    public ResponseResult doooList(Integer page,Integer size,String keyWord,String type,Integer departmentId){
        try{
           return doooService.APIDoooList(page,size,keyWord,type,departmentId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询事项错误",null);
        }
    }

    /**
     * 通过事项ID查询一级情形
     * @param doooId
     * @return
     */
    @GetMapping("/queryDoooSituation")
    public ResponseResult queryDoooSituation(Integer doooId){
        try{
            return doooService.queryDoooSituation(doooId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询事项情形错误",null);
        }
    }

    /**
     * 根据选择ID查询对应情形
     * @param situationDetailsId
     * @return
     */
    @GetMapping("/querySituationDetailsByPid")
    public ResponseResult querySituationDetailsByPid(Integer situationDetailsId){
        try{
            return doooService.querySituationDetailsByPid(situationDetailsId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询事项情形错误",null);
        }
    }

    /**
     * 根据事项ID获取通用材料，选项ID数组获取情形选项
     * @param situationDetailsIds 选项数组
     * @param doooId 事项ID
     * @return
     */
    @PostMapping("/queryDoooDataList")
    public ResponseResult queryDoooDataList(String situationDetailsIds,Integer doooId){
        try{
            return doooService.queryDoooDataList(situationDetailsIds,doooId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询事项材料错误",null);
        }
    }

    /**
     * 获取全部套餐
     * @param type 套餐状态
     * @return
     */
    @GetMapping("/comboList")
    public ResponseResult comboList(String type){
        try{
            return comboService.APIComboList(type);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐错误",null);
        }
    }

    /**
     * 根据套餐ID获取一级情形与选项
     * @param comboId  套餐ID
     * @return
     */
    @GetMapping("/queryComboSituation")
    public ResponseResult queryComboSituation(Integer comboId){
        try{
            return comboService.queryComboSituation(comboId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐情形错误",null);
        }
    }

    /**
     * 根据选项ID获取对应的情形
     * @param comboSituationDetailsId
     * @return
     */
    @GetMapping("/queryComboSituationDetailsByPid")
    public ResponseResult queryComboSituationDetailsByPid(Integer comboSituationDetailsId){
        try{
            return comboService.queryComboSituationDetailsByPid(comboSituationDetailsId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐情形错误",null);
        }
    }

    /**
     * 根据套餐ID获取通用材料，选项ID数组获取情形选项
     * @param comboSituationDetailsIds
     * @param comboId
     * @return
     */
    @PostMapping("/comboSituationDetailsIds")
    public ResponseResult queryComboDataList(String comboSituationDetailsIds,Integer comboId){
        try{
            return comboService.queryComboDataList(comboSituationDetailsIds,comboId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐材料异常",null);
        }
    }

    /**
     * 材料下载
     * @param docId
     * @param response
     */
    @GetMapping("/downDoc")
    @ResponseBody
    public void down_doc(String docId , HttpServletResponse response){

//        String docFilePath =  address + docId +  ".doc";
        String docFilePath = "C:/theme_matter/docFile/材料清单_" + docId +  ".doc";
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try{
            //读取路径下面的文件
            File file = new File(docFilePath);
            String fileName = file.getName();
            if (!file.exists()){
                return;
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            //为文件重新设置名字，采用数据库内存储的文件名称
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO8859-1") + "\"");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (out != null || ips != null){
                    out.close();
                    ips.close();
                }
            } catch (IOException e) {
                System.out.println("关闭流出现异常:" + e);
            }
        }
        return;
    }

    @GetMapping("/getAllSituation")
    public ResponseResult getAllSituation(@RequestParam Integer comboId){
        try{
            return comboService.getAllSituation(comboId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐情形错误",null);
        }
    }

    /**
     * 获取套餐的流程图url
     * @param comboId 套餐id
     * @return
     */
    @GetMapping("/getComboUrl")
    public ResponseResult getComboUrl(@RequestParam Integer comboId){
        try{
            return comboService.getComboUrl(comboId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐情形错误",null);
        }
    }

    /**
     * 根据套餐ID查询所涉及到的部门
     * @param comboId
     * @return
     */
    @GetMapping("/department/getDepartmentByComboId")
    public ResponseResult getDepartmentByComboId(Integer comboId){
        try{
            return comboService.getDepartmentByComboId(comboId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐部门数据错误",null);
        }
    }

    /**
     * 获取热门套餐
     * @return
     */
    @GetMapping("/getHotCombo")
    public ResponseResult getHotCombo(){

            return comboService.getHotCombo();

    }

    /**
     * 获取热门事项
     * @return
     */
    @GetMapping("/getHotDooo")
    public ResponseResult getHotDooo(Integer number){
        try{
            return doooService.getHotDooo(number);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询套餐部门数据错误",null);
        }
    }

    /**
     * 获取全部部门
     * @return
     */
    @GetMapping("/getDepartmentAll")
    public ResponseResult getDepartmentAll(){
        try{
            return departmentService.getDepartmentAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询部门数据错误",null);
        }
    }

    /**
     * 根据事项ID获取办理地址电话
     * @return
     */
    @GetMapping("/getDoooContactDetails")
    public ResponseResult getDoooContactDetails(Integer doooId){
        try{
            return departmentService.getDoooContactDetails(doooId);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(500,"查询部门数据错误",null);
        }
    }
}
