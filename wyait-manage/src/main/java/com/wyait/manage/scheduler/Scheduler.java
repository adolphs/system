package com.wyait.manage.scheduler;

import com.wyait.manage.controller.web2.BusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        File dir=new File(docFilePath);
        removeDir(dir);
    }

    private static void removeDir(File dir) {
        File[] files=dir.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                removeDir(file);
            }else{
                logger.info(file+":"+file.delete());
            }
        }
        logger.info(dir+":"+dir.delete());
    }

    private void cleanUpFiles(File file) {
        //判断文件不为null或文件目录存在
        if (null == file || !file.exists()){
            logger.info("文件删除失败,请检查文件路径是否正确");
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f: files){
            //打印文件名
            String name = file.getName();
            logger.info(name);
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()){
                cleanUpFiles(f);
            }else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }
}
