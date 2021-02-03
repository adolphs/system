package com.wyait.manage.scheduler;

import com.wyait.manage.controller.web2.BusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时器
 */
@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    //每天3:05 执行 清空材料清单
    @Scheduled(cron = "0 05 03 ? * *")
    public void deleteDataList(){
        logger.info("定时任务执行时间：" + dateFormat.format(new Date()));
        //材料清单列表地址
        String docFilePath = "C:/theme_matter/docFile/";
        FileSystemUtils.deleteRecursively(new File(docFilePath));
    }

}
