package com.wyait.manage.controller.web2;

import com.wyait.manage.pojo.FileSituation;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.UploadFile;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.web2.FileSituationService;
import com.wyait.manage.service.web2.UploadFileService;
import com.wyait.manage.utils.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private FileSituationService fileSituationService;
    @RequestMapping("/list")
    public ModelAndView uploadFileList(Integer comboId){
        ModelAndView view = new ModelAndView();
        view.addObject("comboId",comboId);
        view.setViewName("form/uploadFileList");
        return view;
    }

    /**
     * 分页查询附件列表 支持筛选加分页
     * @param page
     * @param limit
     * @param uploadFile
     * @return
     */
    @RequestMapping("/getUploadFileList")
    @ResponseBody
    public PageDataResult getUploadFileList(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit, UploadFile uploadFile){
        logger.debug("分页查询附件列表！搜索条件：uploadFile：" + uploadFile + ",page:" + page
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
            pdr = uploadFileService.getUploadFileList(uploadFile, page, limit);
            logger.debug("附件列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("附件列表查询异常！", e);
        }

        return pdr;
    }


    @ResponseBody
    @RequestMapping("/addUploadFile")
    public String addUploadFile(UploadFile uploadFile){
        logger.debug("附件文件[新增或更新]！uploadFile:" + uploadFile );
        try {
            if (null == uploadFile) {
                logger.debug("附件文件[新增或更新]，结果=请您填写事项信息");
                return "请您输入内容";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置表单项[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            // 设置用户[新增或更新]
            logger.info("附件文件[新增或更新]成功！uploadFile=" + uploadFile + "，操作的用户ID=" + existUser.getId());
            return uploadFileService.addUploadFile(uploadFile);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置表单项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除文件
     * @param fileId  文件id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam String fileId){
        logger.debug("删除附件文件！fileId:" + fileId);
        try{
            return uploadFileService.deleteFile(fileId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除附件文件[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据文件id和情形ID 关联文件
     * @param fileId  文件id
     * @param situationDetailsId  情形id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFileSituation")
    public String addFileSituation(String fileId,Integer situationDetailsId){
        logger.debug("关联附件文件！fileId:" + fileId);
        try{
            return fileSituationService.addFileSituation(fileId,situationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("关联附件文件[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 根据套餐情形ID查看附件列表
     * @param situationDetailsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUploadFiles")
    public List<UploadFile> queryUploadFiles(Integer situationDetailsId){
        logger.debug("查看关联附件文件！situationDetailsId:" + situationDetailsId);
        try{
            return uploadFileService.queryUploadFiles(situationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查看关联附件文件[删除]异常！", e);
            return null;
        }
    }

    @RequestMapping("/deleteuploadByFieldIdAndId")
    @ResponseBody
    public String deleteuploadByFieldIdAndId(Long fileId,Integer situationDetailsId){
        logger.debug("删除关联附件文件！fileId:" + fileId + ",situationDetailsId = " + situationDetailsId);
        try{
            return fileSituationService.deleteuploadByFieldIdAndId(fileId,situationDetailsId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查看关联附件文件[删除]异常！", e);
            return null;
        }
    }
}
