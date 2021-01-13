package com.wyait.manage.dao;

import com.wyait.manage.pojo.ConstructionDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ConstructionDictionaryDAO继承基类
 */
@Mapper
public interface ConstructionDictionaryDAO{
    List<ConstructionDictionary> getConstructionDictionaryList(@Param("constructionDictionary") ConstructionDictionary constructionDictionary);

    int update(ConstructionDictionary constructionDictionary);

    int insert(ConstructionDictionary constructionDictionary);

    int delete(String constructionDictionaryId);
}