package com.wyait.manage.dao;

import com.wyait.manage.pojo.ShippingAddress;
import org.springframework.stereotype.Repository;

/**
 * ShippingAddressDAO继承基类
 */
@Repository
public interface ShippingAddressDAO extends MyBatisBaseDao<ShippingAddress, String> {
}