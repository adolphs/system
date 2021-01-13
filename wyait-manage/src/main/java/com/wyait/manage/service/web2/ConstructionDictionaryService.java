package com.wyait.manage.service.web2;

import com.wyait.manage.pojo.ConstructionDictionary;
import com.wyait.manage.utils.PageDataResult;

public interface ConstructionDictionaryService {
    PageDataResult getConstructionDictionaryList(ConstructionDictionary constructionDictionary, Integer page, Integer limit);

    String addConstructionDictionary(ConstructionDictionary constructionDictionary);

    String delConstructionDictionary(ConstructionDictionary constructionDictionary);
}
