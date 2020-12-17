package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.UploadFile;
import com.wyait.manage.utils.PageDataResult;

public interface UploadFileService {

    PageDataResult getUploadFileList(UploadFile uploadFile, Integer page, Integer limit);

    String addUploadFile(UploadFile uploadFile);

    String deleteFile(String fileId);
}
