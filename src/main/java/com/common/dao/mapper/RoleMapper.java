package com.common.dao.mapper;

import com.common.dao.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}