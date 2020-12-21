package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.controller.web2.FormController;
import com.wyait.manage.dao.FormFieldDAO;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.SysIntfParameter;
import com.wyait.manage.pojo.User;
import com.wyait.manage.pojo.result.ResponseResult;
import com.wyait.manage.utils.IdWorkerUtils;
import com.wyait.manage.utils.PageDataResult;
import com.wyait.manage.utils.SnowflakeIdWorker;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FormServiceImpl implements FormService{
    private static final Logger logger = LoggerFactory.getLogger(FormService.class);
    @Autowired
    FormFieldDAO formFieldDAO;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Override
    public PageDataResult getFormList(FormField formField, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<FormField> formList = formFieldDAO.getFormList(formField);
        // 获取分页查询后的数据
        PageInfo<FormField> pageInfo = new PageInfo<>(formList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(formList);
        return pdr;
    }

    @Override
    public String addFormField(FormField formField) {
        if (formField.getFormFieldId() == null) {
            //新增
            formField.setFormFieldId(snowflakeIdWorker.nextId()+"");
            formFieldDAO.insert(formField);
        }else{
            //修改
            formFieldDAO.updateByFormFieldId(formField);
        }
        return "ok";
    }

    @Override
    public String delFormField(String formFieldId) {
        formFieldDAO.delFormFieldByFormFieldId(formFieldId);
        return "ok";
    }

    @Override
    @Transactional
    public String updateSituationDetailsIdByFormFieldId(String formFieldId, String situationDetailsId,Integer type) {
        formFieldDAO.updateSituationDetailsIdByFormFieldId(formFieldId,situationDetailsId);
        return "ok";
    }

    @Override
    public String deleteSituationDetailsIdByFormFieldId(String formFieldId) {
        formFieldDAO.updateSituationDetailsIdByFormFieldId2(Long.parseLong(formFieldId));
        return "ok";
    }

    @Override
    public ResponseResult batchUploadForm(MultipartFile file, HttpServletRequest request) {
        ResponseResult result = new ResponseResult();
        //创建Excel工作薄
        Workbook workbook = null;
        String prefix="";

        try{
            InputStream inputStream = file.getInputStream();
            if(file!=null) {
                String originalName = file.getOriginalFilename();
                logger.info("============fileName==============" + originalName);
                //获取上传文件的尾缀是否符合execl
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                if (prefix.equals("xls")) {
                    workbook = new HSSFWorkbook(inputStream);
                } else if (prefix.equals("xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                } else {
                    result.setCode(500);
                    result.setErrorMessage("请上传excel文件！");
                    return result;
                }

                //开始创建工作簿
                if (null == workbook) {
                    result.setCode(500);
                    result.setErrorMessage("创建Excel工作薄为空！");
                    return result;
                }
                Row row = null;
                Cell cell = null;
                Sheet sheet = workbook.getSheetAt(0);
                int rowCount = sheet.getPhysicalNumberOfRows();
                List<FormField> fieldList = new ArrayList<>();
                for (int rowNum = 1; rowNum < rowCount; rowNum++) {
                    Row rowData = sheet.getRow(rowNum);
                    if (rowData != null) {
                        FormField formField = new FormField();

                        formField.setFormFieldId(IdWorkerUtils.getInstance().createUUID());
                        formField.setFormFieldName(rowData.getCell(0).getStringCellValue());
                        formField.setFormFieldNameValue(rowData.getCell(1).getStringCellValue());
                        formField.setFormFieldType(String.valueOf(rowData.getCell(2).getBooleanCellValue()).trim());
//                        formField.setFormFieldComboId(rowData.getCell(3).getNumericCellValue());
                        formField.setFormFieldIsBasis(Integer.parseInt(rowData.getCell(4).getStringCellValue()));
                        formField.setFormFieldContent(rowData.getCell(5).getStringCellValue());
                        formField.setFormFieldAnnotation(rowData.getCell(6).getStringCellValue());
                        fieldList.add(formField);
//                        int cellCount = rowData.getPhysicalNumberOfCells();
//                        for (int cellNum = 0; cellNum < cellCount; cellNum++) {
//                            cell = rowData.getCell(cellNum);
//                            if (cell != null) {
//                                int cellType = cell.getCellType();
//                                switch (cellType) {
//                                    case HSSFCell.CELL_TYPE_STRING:
//                                        System.out.print(cell.getStringCellValue());
//                                        break;
//                                    case HSSFCell.CELL_TYPE_BOOLEAN:
//                                        System.out.print(String.valueOf(cell.getBooleanCellValue()));
//                                        break;
//                                    case HSSFCell.CELL_TYPE_BLANK:
//                                        break;
//                                    case HSSFCell.CELL_TYPE_NUMERIC:
//                                        if(HSSFDateUtil.isCellDateFormatted(cell)) {
//                                            Date date = cell.getDateCellValue();
//                                            System.out.print(date);
//                                        }else {
//                                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//                                            System.out.print(cell.toString());
//                                        }
//                                        break;
//                                    case HSSFCell.CELL_TYPE_ERROR:
//                                        break;
//                                }
//                                }
//                                System.out.print(" | ");
//                            }
                        }
//                        System.out.println();
                    System.out.println(fieldList);
                    }
            }

            result.setCode(200);
            result.setErrorMessage("成功");
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            return result;
        }

    }

    @Override
    public FormField getFormPid(String formId) {
        return formFieldDAO.getFormPid(formId);
    }

}
