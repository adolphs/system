package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.UploadFile;

import java.util.List;

public interface FileSituationService {
    String addFileSituation(String fileId, Integer situationDetailsId);

    String deleteuploadByFieldIdAndId(Long fileId, Integer situationDetailsId);
}
