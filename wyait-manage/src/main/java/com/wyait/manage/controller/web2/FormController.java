package com.wyait.manage.controller.web2;

import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.web2.FormService;
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

@RequestMapping("/form")
@Controller
public class FormController {
    private static final Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private FormService formService;

    @RequestMapping("/formDetails")
    public ModelAndView formDetails(Integer comboId){
        ModelAndView view = new ModelAndView();
        view.addObject("comboId",comboId);
        view.setViewName("form/formDetails");
        return view;
    }

    /**
     * 分页查询表单支持筛选加分页
     * @param page
     * @param limit
     * @param formField
     * @return
     */
    @RequestMapping("/getFormList")
    @ResponseBody
    public PageDataResult getFormList(@RequestParam("page") Integer page,
                                                  @RequestParam("limit") Integer limit, FormField formField){
        logger.debug("分页查询表单项列表！搜索条件：sysIntfParameter：" + formField + ",page:" + page
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
            pdr = formService.getFormList(formField, page, limit);
            logger.debug("表单项查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("表单项查询异常！", e);
        }

        return pdr;
    }

    /**
     * 新增表单项   修改表单项
     * @param formField
     * @return
     */
    @PostMapping("/addFormField")
    @ResponseBody
    public String addFormField(FormField formField){
        logger.debug("设置表单项[新增或更新]！sysIntfParameter:" + formField );
        try {
            if (null == formField) {
                logger.debug("表单项[新增或更新]，结果=请您填写事项信息");
                return "您填写事项信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("置表单项[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            // 设置用户[新增或更新]
            logger.info("设置表单项[新增或更新]成功！sysIntfParameter=" + formField + "，操作的用户ID=" + existUser.getId());
            return formService.addFormField(formField);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置表单项[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除
     * @param formFieldId
     * @return
     */
    @PostMapping("/delFormField")
    @ResponseBody
    public String delFormField(String formFieldId){
        logger.debug("删除表单项！formFieldId:" + formFieldId );
        try{
            return formService.delFormField(formFieldId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置表单项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 关联表单项
     * @param formFieldId  表单项id
     * @param situationDetailsId  情形id
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSituationDetailsIdByFormFieldId")
    public String updateSituationDetailsIdByFormFieldId(@RequestParam String formFieldId,@RequestParam String situationDetailsId,Integer type){
        logger.debug("关联表单项！formFieldId:" + formFieldId + " 情形选项! : situationDetailsId" + situationDetailsId);
        try{
            return formService.updateSituationDetailsIdByFormFieldId(formFieldId,situationDetailsId,type);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("关联表单项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除表单项
     * @param formFieldId  表单项id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteSituationDetailsIdByFormFieldId")
    public String deleteSituationDetailsIdByFormFieldId(@RequestParam String formFieldId){
        logger.debug("删除表单项！formFieldId:" + formFieldId);
        try{
            return formService.deleteSituationDetailsIdByFormFieldId(formFieldId);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除表单项[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }
}
