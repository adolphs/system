package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.RolePermissionKey;

import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermissionKey> {
    int deleteByPrimaryKey(RolePermissionKey key);

//    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

	List<RolePermissionKey> findByRole(int roleId);
}