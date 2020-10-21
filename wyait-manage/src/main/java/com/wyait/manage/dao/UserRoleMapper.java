package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.UserRoleKey;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRoleKey> {
    int deleteByPrimaryKey(UserRoleKey key);

//    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

	/**
	 * 根据用户获取用户角色中间表数据
	 * @param userId
	 * @return
	 */
	List<UserRoleKey> findByUserId(int userId);
}