package com.wyait.manage.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author     ：lp
 * @date       ：2020年9月8日 下午10:19:03
 * @version:
 */
public class DocFileListener implements ServletContextListener {
    public static Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("文件下载临时清理任务已启动");
        clearUpDocFile();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
    }

    /**
     * 每天定时清理下载临时文件
     */
    private void clearUpDocFile() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 6); // 控制时，24小时制 设置早上6点批量刷新
            calendar.set(Calendar.MINUTE, 0); // 控制分
            calendar.set(Calendar.SECOND, 0); // 控制秒
            Date date = calendar.getTime();
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new ClearUpWordFileTask(), date, 1000 * 60 * 60 * 24);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * 清理材料文件定时任务
 */
class ClearUpWordFileTask extends TimerTask{

    @Override
    public void run() {
//        String docFilePath = System.getProperty("user.dir") + "/src/main/resources/static/js/theme_matter/docFile/";
        String docFilePath = "C:/theme_matter/docFile/";
        cleanUpFiles(new File(docFilePath));
    }

    private void cleanUpFiles(File file) {
        //判断文件不为null或文件目录存在
        if (null == file || !file.exists()){
            System.out.println("文件删除失败,请检查文件路径是否正确");
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f: files){
            //打印文件名
            String name = file.getName();
            System.out.println(name);
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
