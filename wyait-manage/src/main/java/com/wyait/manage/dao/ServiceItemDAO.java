package com.wyait.manage.dao;

import com.wyait.manage.pojo.ServiceItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceItemDAO继承基类
 */
@Repository
public interface ServiceItemDAO{
    List<ServiceItem> getItemList(@Param("item") ServiceItem item);

    List<ServiceItem> getItemList2(String carryOutCode);

    int insert(ServiceItem serviceItem);

}