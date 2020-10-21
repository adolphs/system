package com.wyait.manage.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *  @Description 生成并导出word文档
 */
public class DocUtils {

    public DocUtils() {
    }

    /**
     * @param lisMaps   通用和情形材料
     * @param templateName 文件名
     * @param dooName 事项名称
     * @Author 2020年9月8日 下午10:19:03
     */
    public static void createWord(List<Map<String, Object>> lisMaps ,Long templateName, String dooName) {
//        String saveFilePath = System.getProperty("user.dir") + "/src/main/resources/static/js/theme_matter/docFile/";
        String saveFilePath = "C:\\theme_matter\\docFile\\";

        File file=new File(saveFilePath);
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            file .mkdir();
        }

        // 创建word文档,并设置纸张的大小
        Document document = new Document(PageSize.A4);
        Table table = null;
        Cell cell = null;
        try {
            RtfWriter2.getInstance(document, new FileOutputStream( saveFilePath + "/材料清单_" + templateName +  ".doc"));
            document.open();

            // 设置标题
            Paragraph ph = new Paragraph();
            Font f = new Font();

            Paragraph p = new Paragraph("欢迎您使用政务服务事项自助查询", new Font(
                    Font.NORMAL, 18, Font.BOLD, new Color(0, 0, 0)));
            p.setAlignment(1);
            document.add(p);
            ph.setLeading(2f);
            ph.setFont(f);

            // 设置中文字体
            // BaseFont bfFont =
//            BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
//                    BaseFont.NOT_EMBEDDED);
//            BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            // Font chinaFont = new Font();

            //创建有3列,1行的表格
            document.add(new Paragraph("您浏览的主题：" + dooName));
            table = new Table(4);
            int[] withs = { 5, 45, 25, 25};  //设置每列所占比例
            table.setWidths(withs);
            table.setBorderWidth(0);
            table.setWidth(100f);

            /**
             * 添加表头元素
             */
            cell = new Cell("根据您已选择的具体条件，您需要提供以下材料：");// 单元格
            cell.setHeader(true);
            cell.setColspan(4);// 设置表格为3列,纵向跨度
            cell.setRowspan(2);// 当前单元格占两行,纵向跨度
            cell.setBackgroundColor(new Color(238, 236, 225));
            table.addCell(cell);

            cell = new Cell("序号");//单元格
            cell.setHeader(true);
            cell.setColspan(1);
            cell.setRowspan(2);
//            cell.setBorderWidth(1);
            table.addCell(cell);

            cell = new Cell("材料名称");//单元格
            cell.setHeader(true);
            cell.setColspan(1);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new Cell("材料要求");
            cell.setHeader(true);
            cell.setColspan(1);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new Cell("备注");
            cell.setHeader(true);
            cell.setColspan(1);
            cell.setRowspan(2);
            table.addCell(cell);
            table.endHeaders();// 表头结束


            int index = 0;
            for (Map<String,Object> map:lisMaps){
                index++;
                cell = new Cell(" " + index + "、 ");
                cell.setColspan(1);
                cell.setRowspan(2);
                table.addCell(cell);

//                cell = new Cell("  " + index + "、" + map.get("dataName").toString());
                cell = new Cell(map.get("dataName").toString());
                cell.setColspan(1);
                cell.setRowspan(2);
                table.addCell(cell);
                //
                cell = new Cell(map.get("dataForm") == null?" ":map.get("dataForm").toString());
                cell.setColspan(1);
                cell.setRowspan(2);
                table.addCell(cell);
                //
                cell = new Cell(map.get("remarks") == null?" ":map.get("remarks").toString());
                cell.setColspan(1);
                cell.setRowspan(2);
                table.addCell(cell);
            }

            //地点和咨询
            cell = new Cell("办理地点和咨询电话：");
            cell.setColspan(4);
            cell.setRowspan(2);
            cell.setBackgroundColor(new Color(238, 236, 225));
            table.addCell(cell);

            cell = new Cell("  办理地点：广州市番禺区亚运大道550号番禺区政务服务中心三楼“一件事专窗”");
            cell.setColspan(4);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new Cell("  咨询电话：020-84690909");
            cell.setColspan(4);
            cell.setRowspan(2);
            table.addCell(cell);

            document.add(table);
            //关闭流
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
