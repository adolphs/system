package com.wyait.manage.controller.web2;

import com.wyait.manage.entity.BusinessDTO;
import com.wyait.manage.pojo.ConstructionDictionary;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.User;
import com.wyait.manage.service.web2.ConstructionDictionaryService;
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

@Controller
@RequestMapping("/constructionDictionary")
public class ConstructionDictionaryController {

    private static final Logger logger = LoggerFactory.getLogger(ConstructionDictionaryController.class);
    @Autowired
    private ConstructionDictionaryService constructionDictionaryService;

    @RequestMapping("/constructionDictionaryList")
    public String ConstructionDictionaryList(){
        return "form/constructionDictionaryList";
    }

    /**
     * 分页查询业务支持筛选加分页
     * @param page
     * @param limit
     * @param constructionDictionary
     * @return
     */
    @RequestMapping("/getConstructionDictionaryList")
    @ResponseBody
    public PageDataResult getConstructionDictionaryList(@RequestParam("page") Integer page,
                                       @RequestParam("limit") Integer limit, ConstructionDictionary constructionDictionary){
        logger.debug("分页查询表建设工程字典表列表！搜索条件：constructionDictionary：" + constructionDictionary + ",page:" + page
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
            pdr = constructionDictionaryService.getConstructionDictionaryList(constructionDictionary, page, limit);
            logger.debug("建设工程字典表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("建设工程字典表查询异常！", e);
        }
        return pdr;
    }


    /**
     * 新增建设工程字典表
     * @param constructionDictionary
     * @return
     */
    @PostMapping("/addConstructionDictionary")
    @ResponseBody
    public String addConstructionDictionary(ConstructionDictionary constructionDictionary){
        logger.debug("设置建设工程字典表[新增或更新]！sysIntfParameter:" + constructionDictionary );
        try {
            if (null == constructionDictionary) {
                logger.debug("建设工程字典表[新增或更新]，结果=请您填写建设工程字典表信息");
                return "请您填写建设工程字典表信息";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("设置建设工程字典表[新增或更新]，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            // 设置用户[新增或更新]
            logger.info("设置建设工程字典表[新增或更新]成功！constructionDictionary=" + constructionDictionary + "，操作的用户ID=" + existUser.getId());
            return constructionDictionaryService.addConstructionDictionary(constructionDictionary);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置建设工程字典表[新增或更新]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }

    /**
     * 删除建设工程字典表
     * @param constructionDictionary
     * @return
     */
    @PostMapping("/delConstructionDictionary")
    @ResponseBody
    public String delConstructionDictionary(ConstructionDictionary constructionDictionary){
        logger.debug("删除建设工程字典表！constructionDictionary:" + constructionDictionary );
        try{
            return constructionDictionaryService.delConstructionDictionary(constructionDictionary);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("设置建设工程字典表[删除]异常！", e);
            return "操作异常，请您稍后再试";
        }
    }
}
