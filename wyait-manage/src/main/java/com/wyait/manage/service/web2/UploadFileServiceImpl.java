package com.wyait.manage.service.web2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.DepartmentMapper;
import com.wyait.manage.dao.UploadFileDAO;
import com.wyait.manage.pojo.Department;
import com.wyait.manage.pojo.FormField;
import com.wyait.manage.pojo.UploadFile;
import com.wyait.manage.utils.IdWorkerUtils;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService{

    @Autowired
    UploadFileDAO uploadFileDAO;
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public PageDataResult getUploadFileList(UploadFile uploadFile, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<UploadFile> uploadFileList = uploadFileDAO.getUploadFileList(uploadFile);
        // 获取分页查询后的数据
        PageInfo<UploadFile> pageInfo = new PageInfo<>(uploadFileList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());

        pdr.setList(uploadFileList);
        return pdr;
    }

    @Override
    public String addUploadFile(UploadFile uploadFile) {
        String departmentId = uploadFile.getDepartmentId();
        Department department = departmentMapper.getDepartmentById(Integer.parseInt(departmentId));
        if (uploadFile.getFileId() == null || uploadFile.getFileId().equals("")){
            //新增
            uploadFile.setCreateTime(new Date());
            uploadFile.setFileId(IdWorkerUtils.getInstance().createUUID());
            uploadFile.setDepartmentName(department.getDepartmentName());
            uploadFileDAO.insert(uploadFile);
        }else{
            //修改
            uploadFile.setDepartmentName(department.getDepartmentName());
            uploadFileDAO.update(uploadFile);
        }
        return "ok";
    }

    @Override
    public String deleteFile(String fileId) {
        uploadFileDAO.delete(fileId);
        return "ok";
    }

    @Override
    public List<UploadFile> queryUploadFiles(Integer situationDetailsId) {
        return uploadFileDAO.queryUploadFiles(situationDetailsId);
    }


}
