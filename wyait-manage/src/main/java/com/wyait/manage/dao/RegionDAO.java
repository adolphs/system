package com.wyait.manage.dao;

import com.wyait.manage.pojo.Region;
import org.springframework.stereotype.Repository;

/**
 * RegionDAO继承基类
 */
@Repository
public interface RegionDAO extends MyBatisBaseDao<Region, Region> {
}